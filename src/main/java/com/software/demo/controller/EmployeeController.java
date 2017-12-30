package com.software.demo.controller;

import com.software.demo.Bean.FileUploadBean;
import com.software.demo.Entity.Data;
import com.software.demo.Entity.Employee;
import com.software.demo.Entity.Positon;
import com.software.demo.Entity.Student;
import com.software.demo.Repository.EmployeeRepository;
import com.software.demo.Repository.PositionRepository;
import com.software.demo.Repository.StudentRepository;
import com.software.demo.Utils.UtilDate;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private FileUploadBean fileUploadBean;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PositionRepository positionRepository;
    /*登录验证端口*/
    @RequestMapping(value = "/user_login",method = RequestMethod.POST)
    public String temp(Model model,@RequestParam("username") String name, @RequestParam("password") String pass, HttpServletResponse response){
        Employee employee;
        employee=employeeRepository.findByAccountAndPassword(name,pass);
        if(employee!=null){
            Cookie cookie = new Cookie("id",employee.getId()+"");
            Cookie cookie2 = new Cookie("password",employee.getPassword()+"");
            response.addCookie(cookie);
            model.addAttribute("data",employee);
            return "admin_index";
        }
        employee=employeeRepository.findByEmailAndPassword(name,pass);
        if(employeeRepository.findByEmailAndPassword(name,pass)!=null){
            Cookie cookie = new Cookie("id",employee.getId()+"");
            Cookie cookie2 = new Cookie("password",employee.getPassword()+"");
            response.addCookie(cookie);
            model.addAttribute("data",employee);
            return "admin_index";
        }
        employee=employeeRepository.findByPhoneAndPassword(name,pass);
        if(employeeRepository.findByPhoneAndPassword(name,pass)!=null){
            Cookie cookie = new Cookie("id",employee.getId()+"");
            Cookie cookie2 = new Cookie("password",employee.getPassword()+"");
            response.addCookie(cookie);
            model.addAttribute("data",employee);
            return "admin_index";
        }
        return "login";
    }

     /*  添加用户端口*/
    @RequestMapping(value = "/user_register",method = RequestMethod.POST)
    public String register(Model model, @RequestParam("account") String account, @RequestParam("password") String pass,
                           @RequestParam("phone") String phone, @RequestParam("gender") Integer gender,
                           @RequestParam("name")String name, @RequestParam("email") String email,@RequestParam("position") int positon_id,
                           @RequestParam("status") Integer status){
        Employee employee=new Employee();
        employee.setAccount(account);
        employee.setPassword(pass);
        employee.setPhone(phone);
        employee.setGender(gender);
        employee.setName(name);
        employee.setEmail(email);
        Positon position=positionRepository.findOne(positon_id);
        employee.setPosition(position);
        employee.setStatus(status);
        employee.setHead("upload//orghead.jpg");
        try{
            employeeRepository.save(employee);
            model.addAttribute("name",name);
            return "redirect:admin_employee_panel";
        }catch (Exception e){
            return "redirect:admin_employee_panel";
        }

    }
    /*  用户信息更改*/
    @RequestMapping(value = "/admin_index_mine_update",method = RequestMethod.POST)
    public String     admin_index_update(Model model, @RequestParam("account") String account, @RequestParam("password") String pass,
                           @RequestParam("phone") String phone, @RequestParam("gender") Integer gender,
                           @RequestParam("name")String name, @RequestParam("email") String email,
                           @RequestParam("status") String status,@RequestParam("file") MultipartFile file){
        Employee employee=employeeRepository.findByAccount(account);
        employee.setPassword(pass);
        employee.setPhone(phone);
        employee.setGender(gender);
        employee.setEmail(email);
        long num=new java.util.Date().getTime();
        if(!file.isEmpty()){
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            if(fileUploadBean.upload(file,num+suffixName)){
                employee.setHead("upload//"+num+suffixName);
            }
        }
        try{
            employeeRepository.save(employee);
            return "redirect:admin_index_mine";
        }catch (Exception e){
            return "fail";
        }

    }

    @RequestMapping(value = "/register_index",method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/admin_index",method = RequestMethod.GET)
    public String admin_index(Model model,HttpServletRequest request){
        String id ="null";
        //获取所有Cookie
        Cookie[] cookies = request.getCookies();
        //如果浏览器中存在Cookie
        if (cookies != null && cookies.length > 0) {
            //遍历所有Cookie
            for(Cookie cookie: cookies) {
                //找到name为city的Cookie
                if (cookie.getName().equals("id")) {
                    //使用URLDecode.decode()解码,防止中文乱码
                    id = cookie.getValue();
                }
            }
        }
        if(id.isEmpty()){
            return "admin_login";
        }
        Employee employee=employeeRepository.findOne(Integer.parseInt(id));
        model.addAttribute("data",employee);
        return "admin_index";
    }

    @RequestMapping(value = "admin_index_update",method = RequestMethod.POST)
    public String admin_index_update(){

        return  "redirect:admin_index_mine";
    }


    /**
     * 员工管理面板
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "admin_employee_panel",method = RequestMethod.GET)
    public String admin_employee_panel(Model model,HttpServletRequest request){
        String id  ;
        //获取所有Cookie
        Cookie[] cookies = request.getCookies();
        //如果浏览器中存在Cookie
        if (cookies != null && cookies.length > 0) {
            //遍历所有Cookie
            for(Cookie cookie: cookies) {
                //找到name为city的Cookie
                if (cookie.getName().equals("id")) {
                    //使用URLDecode.decode()解码,防止中文乱码
                    id = cookie.getValue();
                    Employee employee=employeeRepository.findOne(Integer.parseInt(id));
                    model.addAttribute("data",employee);
                    List<Employee> lists=employeeRepository.findAll();
                    model.addAttribute("employees",lists);
                    return "admin_employee_panel";
                }
            }
        }
        return "admin_login";
        //return "forward:/find_position_by_id?id="+employee.getPosition_id();
    }



    @RequestMapping(value = "admin_index_mine",method = RequestMethod.GET)
    public String admin_index_mine(Model model,HttpServletRequest request){
        String id  ;
        //获取所有Cookie
        Cookie[] cookies = request.getCookies();
        //如果浏览器中存在Cookie
        if (cookies != null && cookies.length > 0) {
            //遍历所有Cookie
            for(Cookie cookie: cookies) {
                //找到name为city的Cookie
                if (cookie.getName().equals("id")) {
                    //使用URLDecode.decode()解码,防止中文乱码
                    id = cookie.getValue();
                    Employee employee=employeeRepository.findOne(Integer.parseInt(id));
                    model.addAttribute("data",employee);
                    Positon positon=positionRepository.findOne(employee.getPosition().getId());
                    model.addAttribute("positon",positon);
                    return "admin_index_mine";
                }
            }
        }
        return "admin_login";
       //return "forward:/find_position_by_id?id="+employee.getPosition_id();
    }

    @GetMapping(value = "admin_employee_panel_add")
    public String admin_employee_panel_add(HttpServletRequest request,Model model){
        String id  ;
        //获取所有Cookie
        Cookie[] cookies = request.getCookies();
        //如果浏览器中存在Cookie
        if (cookies != null && cookies.length > 0) {
            //遍历所有Cookie
            for(Cookie cookie: cookies) {
                //找到name为city的Cookie
                if (cookie.getName().equals("id")) {
                    //使用URLDecode.decode()解码,防止中文乱码
                    id = cookie.getValue();
                    Employee employee=employeeRepository.findOne(Integer.parseInt(id));
                    model.addAttribute("data",employee);
                    List<Positon> positon=positionRepository.findAll();
                    model.addAttribute("positions",positon);
                    return "admin_employee_panel_add";
                }
            }
        }
        return "admin_login";
    }

}
