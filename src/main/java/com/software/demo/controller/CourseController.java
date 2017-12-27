package com.software.demo.controller;

import com.software.demo.Entity.Course;
import com.software.demo.Entity.Employee;
import com.software.demo.Entity.Student;
import com.software.demo.Repository.CourseRepository;
import com.software.demo.Repository.EmployeeRepository;
import com.software.demo.Repository.StudentRepository;
import com.software.demo.Utils.UtilDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "/admin_course_panel",method = RequestMethod.GET)
    public String temp(Model model,HttpServletRequest request){
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
                    List<Course> course=courseRepository.findAll();
                    model.addAttribute("courses",course);
                    return "admin_course_panel";

                }
            }
        }
        return "admin_login";
    }

/*
    *//*查询所有*//*
    @RequestMapping(value = "/students",method = RequestMethod.GET)
    public List<Student> stuendtList(){
        return studentRepository.findAll();
    }

    *//*查询指定id*//*
    @GetMapping(value = "/student_id")
    public Student studentbyid(@RequestParam("id") int id){
        return  studentRepository.findOne(id);
    }
    *//*模拟登录*//*
    @GetMapping(value = "/admin_student_panel")
    public String Login(Model model, HttpServletRequest request){
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
                    List<Student> student=studentRepository.findAll();
                    model.addAttribute("students",student);
                    //if(student.get)
                    return "admin_student_panel";

                }
            }
        }
        return "admin_login";
    }
   *//* 模糊查询名字*//*
   @GetMapping(value = "/admin_student_panel_list_search")
   public String studentbyname(Model model,@RequestParam("name") String name,HttpServletRequest request){
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
                   name="%"+name+"%"; //添加前后模糊查询通配符
                   List<Student> students =  studentRepository.findByNameLike(name);
                   model.addAttribute("students",students);
                   return "admin_student_panel";

               }
           }
       }
       return "admin_login";
   }
   */

    /**
     *添加学生列表
     *admin_course_panel_students
     * */
    @GetMapping(value = "/admin_course_panel_students")
    public String admin_course_panel_students(Model model,HttpServletRequest request){
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
                    List<Employee> employees=employeeRepository.findAll();
                    model.addAttribute("teachers",employees);
                    return "admin_course_panel_students";
                }
            }
        }
        return "admin_login";
    }

    //* 学生添加界面*//*
    @GetMapping(value = "/admin_course_panel_add")
    public String admin_student_panel_add(Model model,HttpServletRequest request){
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
                    List<Employee> employees=employeeRepository.findAll();
                    model.addAttribute("teachers",employees);
                    return "admin_course_panel_add";
                }
            }
        }
        return "admin_login";
    }
    /*

    添加课程
     */
    @PostMapping(value = "/admin_course_panel_add_data")
    public String addstudent(@RequestParam("name") String name,@RequestParam("time") Integer time,@RequestParam("teacher") Integer id,@RequestParam("describ") String describ
                           ){
        Course course=new Course();
        Employee employee=employeeRepository.findOne(id);
        course.setEmployee(employee);
        course.setName(name);
        course.setDecrib(describ);
        course.setTime(time);
        course.setInputdate(new java.util.Date().getTime());
        courseRepository.save(course);
        return "redirect:admin_course_panel";
    }

}
