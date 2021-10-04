package com.example.mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatis_plus.mapper.UserMapper;
import com.example.mybatis_plus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name").
                isNotNull("email").
                ge("age", 12);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    void contextLoads2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "jack");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    void contextLoads3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 2, 10);
        Long count = userMapper.selectCount(wrapper);
        System.out.println("=======>>Result is" + count);
    }

    @Test
    void contextLoads4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //like right的意思是 LIKE '值%'，like left就是LIKE '%值'
        //NOT LIKE '%值%
        //LIKE '%值%'
        wrapper.notLike("name", "4")
                .likeRight("name", "测试");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }


    @Test
    void contextLoads5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "select id from user where id < 30");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    @Test
    void contextLoads6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }
}
