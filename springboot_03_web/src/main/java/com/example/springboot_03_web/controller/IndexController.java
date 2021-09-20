package com.example.springboot_03_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;


//在 templates下的所有页, 只能通过controller来跳转!
@Controller
public class IndexController {

    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("msg","<h2>hello,spring</h2>");
        model.addAttribute("users", Arrays.asList("this","is","a","string","array"));
        return "test";
    }

    public String index(){
        return "test";
    }
}
