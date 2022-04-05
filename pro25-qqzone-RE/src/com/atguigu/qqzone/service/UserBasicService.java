package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

public interface UserBasicService {
    UserBasic login(String loginId , String pwd );
    List<UserBasic> getFriendList(UserBasic userBasic);
    //get user information via id
    UserBasic getUserBasicById(Integer id);



}
