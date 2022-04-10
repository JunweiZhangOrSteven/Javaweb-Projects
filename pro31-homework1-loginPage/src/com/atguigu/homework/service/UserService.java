package com.atguigu.homework.service;


import com.atguigu.homework.pojo.User;

public interface UserService {
    User login(String uname , String pwd );
    void regist(User user);
}
