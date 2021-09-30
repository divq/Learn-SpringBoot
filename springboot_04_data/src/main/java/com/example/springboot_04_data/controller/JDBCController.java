package com.example.springboot_04_data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //查询数据库的所有信息
    @GetMapping("/getUserListAction")
    public List<Map<String, Object>> userList() {
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    @GetMapping("/addUserAction")
    public String addUserAction() {
        String sql = "insert into mybatis.user(id,name,pwd) values(4,'小明','123456')";
        jdbcTemplate.update(sql);
        return "add-OK";
    }

    @GetMapping("/updateUserAction")
    public String updateUserAction(int id) {
        String sql = "update mybatis.user set name=?,pwd=? where id=" + id;
        Object[] objects = new Object[2];
        objects[0] = "小明2";
        objects[1] = "9999";
        jdbcTemplate.update(sql, objects);
        return "update-OK";
    }

    @GetMapping("/deleteUserAction")
    public String deleteUserAction(int id) {
        String sql = "delete from mybatis.user where id=?";
        jdbcTemplate.update(sql, id);
        return "delete-OK";
    }


}
