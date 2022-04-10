package com.atguigu.homework.dao;

import com.atguigu.homework.pojo.User;

public interface UserDAO {
    User getUser(String uname , String pwd );
    void addUser(User user);
}
