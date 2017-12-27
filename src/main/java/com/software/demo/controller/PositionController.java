package com.software.demo.controller;

import com.software.demo.Entity.Employee;
import com.software.demo.Entity.Permission;
import com.software.demo.Repository.EmployeeRepository;
import com.software.demo.Repository.PermissionRepository;
import com.software.demo.Repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.software.demo.Entity.Positon;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PositionController {
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PermissionRepository permissionRepository;
    /*list*/
    @PostMapping(value = "/add_position_detail")
    public String add(){
        //positionRepository.save();
        return "";
    }
    @GetMapping(value = "/find_position_by_id")
    public String find_position_by_id(Model model, @RequestParam("id") Integer id, HttpServletResponse response){
        Positon positon=positionRepository.findOne(id);
        model.addAttribute("positon",positon.getName());
        return "admin_index_mine";
    }
    @GetMapping(value = "/admin_employee_panel_add_position")
    public String admin_employee_panel_add_position(Model model, HttpServletResponse response,HttpServletRequest request){
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
                    List<Permission> permissions=permissionRepository.findAll();
                    model.addAttribute("permissions",permissions);
                    return "admin_employee_panel_add_position";
                }
            }
        }
        return "admin_login";
    }
    @PostMapping(value = "/admin_employee_panel_add_position")
    public String admin_employee_panel_add_position(){

        return "redirect:admin_employee_panel_add_position";
    }
}
