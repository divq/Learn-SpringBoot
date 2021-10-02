package com.example.swagger_demo.controller;

import com.example.swagger_demo.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Hello控制器")
@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @ApiOperation(value = "得到用户")
    @GetMapping(value = "/user")
    public User user() {
        return new User();
    }

    @PostMapping(value = "/addUser")
    public User addUser(User user) {
        return user;
    }

}
