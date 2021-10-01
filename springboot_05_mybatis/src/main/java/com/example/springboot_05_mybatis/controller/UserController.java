package com.example.springboot_05_mybatis.controller;

import com.example.springboot_05_mybatis.mapper.UserMapper;
import com.example.springboot_05_mybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/queryAllUsers")
    public List<User> queryAllUsers() {
        return userMapper.queryAllUsers();
    }
}
