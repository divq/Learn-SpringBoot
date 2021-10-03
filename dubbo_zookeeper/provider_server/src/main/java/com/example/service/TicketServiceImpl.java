package com.example.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service//这里面的service是dubbo的，不是spring的，
// 所以你得把本来的spring 的service注解改成Component，反正功能是一样的
@Component
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "这里是测试的serviceImpl";
    }
}
