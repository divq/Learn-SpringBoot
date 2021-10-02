package com.example.springboot_09_task.controller;

import com.example.springboot_09_task.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    AsyncService asyncService;

    @Autowired
    public void setAsyncService(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @RequestMapping("/hello")
    public String hello() {
        asyncService.hello();
        return "OK";
    }
}
