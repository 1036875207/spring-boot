package com.software.demo.controller;

import com.software.demo.Entity.Positon;
import com.software.demo.Entity.Permission;
import com.software.demo.Repository.PermissionRepository;
import com.software.demo.Repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    /*获取application.yml配置文件中的属性*/
    @Value("${name}")
    private String name;
    /*测试*/
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }
    /*登录入口*/
    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String declare(){
        return "admin_login";
    }


    @RequestMapping(value = "/admin_register",method = RequestMethod.GET)
    public String register(Model model){
        List<Positon> list=positionRepository.findAll();
        model.addAttribute("data",list);
        return "admin_add_Employee";
    }
    @RequestMapping(value = "add_pisition",method = RequestMethod.GET)
    public  String  add_position(Model model){
        List<Permission> list=permissionRepository.findAll();
        model.addAttribute("data",list);
        return "admin_add_position";
    }

    /*登出，消除信息*/
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public  String  logout(Model model, HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for(int i=0;i<cookies.length;i++)
        {
            Cookie cookie = new Cookie(cookies[i].getName(), null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return "admin_login";
    }
}
