package com.example.simple_web.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user_info = request.getSession().getAttribute("user_info");
        String requestURL = request.getRequestURI();
        if (!requestURL.equals("/index") && user_info == null) {
            request.setAttribute("msg", "没有权限，请先登录");
            request.getRequestDispatcher("/index").forward(request, response);
            return false;
        } else {
            return true;
        }
    }
}
