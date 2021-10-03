package com.example.shiro_springboot.service;

import com.example.shiro_springboot.mapper.UserMapper;
import com.example.shiro_springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public int addUser(User myUser) {
        return 0;
    }

    @Override
    public int deleteUserById(int id) {
        return 0;
    }

    @Override
    public Integer updateUser(User myUser) {
        return null;
    }

    @Override
    public User queryUserById(int id) {
        return null;
    }

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }

    @Override
    public List<User> queryAllUsers() {
        return null;
    }
}
