package com.example.shiro_springboot.service;

import com.example.shiro_springboot.pojo.User;

import java.util.List;

public interface UserService {
    int addUser(User myUser);

    int deleteUserById(int id);

    Integer updateUser(User myUser);

    User queryUserById(int id);

    User queryUserByName(String name);

    List<User> queryAllUsers();
}
