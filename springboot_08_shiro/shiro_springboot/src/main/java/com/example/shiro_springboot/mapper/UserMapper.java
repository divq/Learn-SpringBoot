package com.example.shiro_springboot.mapper;


import com.example.shiro_springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    int addUser(User myUser);

    int deleteUserById(int id);

    Integer updateUser(User myUser);

    User queryUserById(int id);

    User queryUserByName(String name);

    List<User> queryAllUsers();
}

