package com.syd.shiro_springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model) {
        //把数据存入model
        model.addAttribute("name", "syd");
        return "test";
    }

    @RequestMapping("/login")
    public String login(Model model, String name, String password) {
        /**
         * shiro编写认证操作
         */
        //1、获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        //3、执行登录方法
        try {
            subject.login(token);//会去到UserRealm的认证方法出进行认证。
            //登陆成功 跳转到主页（test.html）
            return "redirect:/testThymeleaf";

        } catch (UnknownAccountException e) {
//            e.printStackTrace();
            //登录失败：用户名不存在
            model.addAttribute("msg", "用户名不存在");
//            return "redirect:/toLogin";//不写redirect 相当于跳转到一个页面(model数据传不过去)，写上相当于是一个请求。
            return "login";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            //登录失败：密码错误
            model.addAttribute("msg", "密码错误");
            return "login";
        }

    }

    @RequestMapping("/add")
    public String add(Model model) {
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update(Model model) {
        return "/user/update";
    }

    @RequestMapping("/noAuth")
    public String noAuth(Model model) {
        return "/user/noAuth";
    }

    @RequestMapping("/toLogin")
    public String toLogin(Model model) {
        return "/login";
    }

}
