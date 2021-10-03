package com.example.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    //这里RPC调用provider里面的method
    @Reference//引用。 pom坐标，可以定义路径相同的接口名
    TicketService ticketService;

    public void buyTicket() {
        String ticket = ticketService.getTicket();
        System.out.println("在注册中心拿到一张票");
    }
}
