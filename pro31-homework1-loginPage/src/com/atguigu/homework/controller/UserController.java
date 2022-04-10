package com.atguigu.homework.controller;


import com.atguigu.homework.pojo.User;
import com.atguigu.homework.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController {

    private UserService userService ;


    public String login(String uname , String pwd , HttpSession session){

        User user = userService.login(uname, pwd);
        if(user!=null){
            session.setAttribute("currUser",user);
            return "redirect:page.do?operate=page&page=index";
        }
        return "redirect:page.do?operate=page&page=login";
    }

    public String regist(String verifyCode , String uname , String pwd , String email , HttpSession session , HttpServletResponse response) throws IOException {
        Object kaptchaCodeObj = session.getAttribute("KAPTCHA_SESSION_KEY");
        if(kaptchaCodeObj==null || !verifyCode.equals(kaptchaCodeObj)){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script language='javascript'>alert('验证码不正确！');window.location.href='page.do?operate=page&page=user/regist';</script>");
            //out.println("<script language='javascript'>alert('验证码不正确！');</script>");
            //return "user/regist";
            return "redirect:page.do?operate=page&page=regist";
        }else{
            if(verifyCode.equals(kaptchaCodeObj)){
                User user = new User(uname , pwd , email,0);
                session.setAttribute("currUser",user);
                userService.regist(user);
                return "redirect:page.do?operate=page&page=login";
            }
        }
        return "redirect:page.do?operate=page&page=login";
    }
}
