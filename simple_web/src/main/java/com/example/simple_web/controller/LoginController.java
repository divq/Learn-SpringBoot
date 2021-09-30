package com.example.simple_web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @PostMapping("/user/loginAction")
    public String loginAction(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
        if (!StringUtils.isEmpty(username) && password.equals("123")) {
            session.setAttribute("user_info", username);
            return "redirect:main";
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            return "/index";
        }
    }

    @GetMapping("/user/signOutAction")
    public String signOutAction(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }
}
