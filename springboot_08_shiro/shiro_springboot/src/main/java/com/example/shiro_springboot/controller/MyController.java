package com.example.shiro_springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class MyController {
    @RequestMapping({"/", "/index"})
    public String indexPage(Model model) {
        model.addAttribute("msg", "hello, shiro");
        return "index";
    }

    @RequestMapping("/updatePage")
    public String updatePage() {
        return "update";
    }

    @RequestMapping("/addPage")
    public String addPage() {
        return "add";
    }


    @RequestMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/loginAction")
    public String loginAction(String username, String password, Model model, HttpSession session) {

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);//执行登陆方法，如果无异常就OK
            session.setAttribute("currentUser", username);
            return "index";
        } catch (UnknownAccountException uae) {
            model.addAttribute("msg", "There is no user with username of " + token.getPrincipal());
            return "login";
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg", "Password for account " + token.getPrincipal() + " was incorrect!");
            return "login";
        } catch (LockedAccountException lae) {
            model.addAttribute("msg", "The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
            return "login";
        }
    }

    @GetMapping("/logoutAction")
    public String logoutAction(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:index";
    }

    @RequestMapping("/noAuth")
    @ResponseBody
    public String unauthorized() {
        return "未经授权无法访问此页面";
    }
}
