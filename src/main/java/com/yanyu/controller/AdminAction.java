package com.yanyu.controller;

import com.yanyu.pojo.Admin;
import com.yanyu.service.AdminService;
import com.yanyu.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminAction {

    //业务逻辑层对象
    @Autowired
    AdminService adminService;

    //实现登陆的判断,进行相应跳转、
    @RequestMapping("/login")
    public String login(String name, String pwd, HttpServletRequest request){
        Admin admin = adminService.login(name,pwd);
        if(admin!=null){
            //登陆成功，跳转到main.jsp页面（视图解析器）
            request.setAttribute("admin",admin); // 存储用户信息
            return "main";
        }else {
            //登陆失败,跳转到login.jsp页面（视图解析器）
            request.setAttribute("errmsg","用户名或密码不正确！"); //返回失败信息
            return "login";
        }
    }

    //实现注册
    @RequestMapping("/regist")
    public String regidt(String myname, String mypwd, HttpServletRequest request){
        int num = adminService.regist(myname, mypwd);
        if(num>0){
            //注册成功
            return "login";
        }else {
            //注册失败
            request.setAttribute("errmsg","注册失败！"); //返回失败信息
            return "regist";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/repeat",produces = "text/html;charset=UTF-8")
    public Object repeat(String name,HttpServletRequest request){

        Admin admin = adminService.repeat(name);
        if(admin!=null){
            return "用户已存在!";
        }else {
            //登陆失败,跳转到login.jsp页面（视图解析器）
            return "用户名可以使用！";
        }
    }
}
