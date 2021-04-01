package com.offcn.springbootshiro.controller;

import com.offcn.springbootshiro.service.UserServiceImpl;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @RequestMapping("login")
    public String login(String userName,String userPwd){
        try {
            userService.checkLogin(userName,userPwd);
            System.out.println("-----------登录成功-------------");
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("----------登录失败-------------");
            return "login";
        }

    }
}
