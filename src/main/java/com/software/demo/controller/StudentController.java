package com.software.demo.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.software.demo.Entity.*;
import com.software.demo.Repository.CourseRepository;
import com.software.demo.Repository.EmployeeRepository;
import com.software.demo.Repository.GradeRepository;
import com.software.demo.Repository.StudentRepository;
import com.software.demo.Utils.UtilDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private CourseRepository courseRepository;
    @RequestMapping(value = "/temp",method = RequestMethod.GET)
    public String temp(){
        return "index";
    }


    /*查询所有*/
    @RequestMapping(value = "/students",method = RequestMethod.GET)
    public List<Student> stuendtList(){
        return studentRepository.findAll();
    }

    /*查询指定id*/
    @GetMapping(value = "/student_id")
    public Student studentbyid(@RequestParam("id") int id){
        return  studentRepository.findOne(id);
    }
    /*模拟登录*/
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
   /* 模糊查询名字*/
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
    /* 学生添加界面*/
    @GetMapping(value = "/admin_student_panel_add")
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
                    return "admin_student_panel_add";
                }
            }
        }
        return "admin_login";
    }
    /*学生信息更新界面*/
    @GetMapping(value = "/admin_student_panel_list_detail")
    public String admin_student_panel_list_detail(Model model,HttpServletRequest request,@RequestParam("id") Integer stid){
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
                    Student student=studentRepository.findOne(stid);
                    model.addAttribute("student",student);
                    return "admin_student_panel_list_detail";
                }
            }
        }
        return "admin_login";
    }

    /**admin_student_panel_grade_search
     *admin_student_panel_grade


     */
    /*学生信息更新界面*/
    @GetMapping(value = "/admin_student_panel_grade")
    public String admin_student_panel_grade(Model model,HttpServletRequest request){
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
                    List<Grade> grade=gradeRepository.findAll();
                    model.addAttribute("grades",grade);
                    return "admin_student_panel_grade";
                }
            }
        }
        return "admin_login";
    }


    /**
     *按条件查询成绩
     */
    @GetMapping(value = "/admin_student_panel_grade_search")
    public String admin_student_panel_grade_search(Model model,HttpServletRequest request,@RequestParam("name") String name,@RequestParam("option") String option){
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
                    List<Grade> grade=new ArrayList<>();
                    switch (option){
                        case "课程":
                            name="%"+name+"%";
                            List<Course> courses=courseRepository.findByNameLike(name);
                            for (Course course : courses) {
                               List<Grade> gra= gradeRepository.findByCourse_Id(course.getId());
                                for (Grade g : gra) {
                                    grade.add(g);
                                }
                            }
                            model.addAttribute("grades",grade);
                            break;
                        case "学生姓名":
                            name="%"+name+"%";
                            List<Student> students=studentRepository.findByNameLike(name);
                            for (Student student : students) {
                                List<Grade> gra= gradeRepository.findByStudent_id(student.getId());
                                for (Grade g : gra) {
                                    grade.add(g);
                                }
                            }
                            model.addAttribute("grades",grade);
                            break;
                        case "老师":
                           // Employee employee1=employeeRepository.findByName(name);
                           // List<Grade> gradess=gradeRepository.findByStudent_id(employee1.getId());
                           // model.addAttribute("grades",gradess);
                            break;
                        default:
                            return "redirect:admin_student_panel_grade";
                    }
                    return "admin_student_panel_grade";
                }
            }
        }
        return "admin_login";
    }
    /*
    *添加学生
    * birth 需要在前端转为long
    * gender 前端传 0 1
    * */
    @PostMapping(value = "/admin_student_panel_add_data")
    public String addstudent(@RequestParam("name") String name,@RequestParam("username") String username,@RequestParam("grade") String grade,
                              @RequestParam("address") String address,@RequestParam("phone")String phone1,
                              @RequestParam("gender")Integer gender,
                              @RequestParam("school")String school){
        Student student=new Student();
        student.setName(name);
        student.setUsername(username);
        student.setGreade(grade);
        student.setGender(gender);
        student.setPhone1(phone1);
        student.setSchool(school);
        student.setAddress(address);
        student.setStatus(0);
        student.setHead("upload//orghead.jpg");
        try {
            student.setInputDate(UtilDate.getNowLong());
        }catch (Exception e){
            e.printStackTrace();
        }
        studentRepository.save(student);
        return "redirect:admin_student_panel";
    }


    /*修改学生*/

    @PostMapping(value = "/updateStudent")
    public Student updateStudent(@RequestParam("id") int id,@RequestParam("name") String name,@RequestParam("birth") Long birth,
                              @RequestParam("address") String address,@RequestParam("phone")String phone1,
                              @RequestParam("phone2")String phone2,@RequestParam("gender")Integer gender,
                              @RequestParam("school")String school){
        Student student=new Student();
        student.setId(id);
        student.setName(name);
        student.setBirth(birth);
        student.setGender(gender);
        student.setPhone1(phone1);
        student.setPhone2(phone2);
        student.setSchool(school);
        student.setAddress(address);
        System.out.print(address);
        student.setStatus(0);
        try {
            student.setInputDate(UtilDate.getNowLong());
        }catch (Exception e){
            e.printStackTrace();
        }
        studentRepository.save(student);
        return student;
    }
}
