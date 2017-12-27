package com.software.demo.controller;

import com.software.demo.Entity.Daily;
import com.software.demo.Entity.Employee;
import com.software.demo.Repository.DailyRepository;
import com.software.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DailyController {

    @Autowired
    DailyRepository dailyRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    /***
     *
     *  后台管理——代办事宜
     *
     * @param model
     * @param request
     * @return
     */
    @GetMapping(value = "/admin_index_daily")
    public String admin_index_daily(Model model, HttpServletRequest request){
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
                    List<Daily> daily=dailyRepository.findByEmployeeid(employee.getId());
                    model.addAttribute("dailys",daily);
                    return "admin_index_daily";
                }
            }
        }
        return "admin_login";

    }
    /***
     *
     * 自添加代办事宜
     */
    @GetMapping(value = "/admin_index_daily_add_daily")
    public String admin_index_daily_add_daily(){

    return "admin_index_daily";
    }

}
