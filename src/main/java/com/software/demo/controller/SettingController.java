package com.software.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingController {

    /*获取application.yml配置文件中的属性*/
    @Value("${name}")
    private String name;

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(){
        return "Hello Spring Boot";
    }
    @RequestMapping(value = "/declare",method = RequestMethod.GET)
    public String declare(){
        return name;
    }
}
