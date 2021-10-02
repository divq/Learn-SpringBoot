package com.example.springboot_09_task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot09TaskApplicationTests {
    JavaMailSenderImpl javaMailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Test
    void contextLoads() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("SpringBoot邮件测试");
        simpleMailMessage.setText("这是邮件的正文");
        simpleMailMessage.setTo("removed@qq.com");
        simpleMailMessage.setFrom("removed@qq.com");

        javaMailSender.send(simpleMailMessage);
    }


    @Test
    void contextLoads2() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");


        helper.setSubject("SpringBoot邮件测试");
        helper.setText("<h1 style='color:red'>这是邮件的正文，而且开启了html支持</h1>", true);
        helper.addAttachment("1.jpg", new File("/Users/removed/test.jpg"));

        helper.setTo("removed@qq.com");
        helper.setFrom("removed@qq.com");

        javaMailSender.send(mimeMessage);
    }
}
