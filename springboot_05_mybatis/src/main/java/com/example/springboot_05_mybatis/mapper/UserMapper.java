package com.example.springboot_05_mybatis.mapper;

import com.example.springboot_05_mybatis.pojo.User;
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

    List<User> queryAllUsers();
}
