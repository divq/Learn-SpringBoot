package com.example.springboot_09_task.controller;

import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    public void hello() {
        System.out.println("hello被执行了");
    }
}
