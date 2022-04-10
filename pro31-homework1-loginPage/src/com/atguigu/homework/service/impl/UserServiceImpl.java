package com.atguigu.homework.service.impl;


import com.atguigu.homework.dao.UserDAO;
import com.atguigu.homework.pojo.User;
import com.atguigu.homework.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO ;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname,pwd);
    }

    @Override
    public void regist(User user) {
        userDAO.addUser(user);
    }
}
