package com.example.springboot_06_springsecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问，但是功能页只有对应的有权限的人才能访问
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //没有权限就默认到登录页
        //这里面会跳转到/login，是自动生成的，不是我我们写的。
        http.formLogin().loginPage("/loginPage").loginProcessingUrl("/login");


        //开启注销功能
        http.logout().logoutSuccessUrl("/index");

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember-me");
    }

    //密码编码需要加密
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("vip3@1.com")
                .password(passwordEncoder.encode("123456"))
                .roles("vip3")
                .and()
                .withUser("vip1@1.com")
                .password(passwordEncoder.encode("123456"))
                .roles("vip1")
                .and()
                .withUser("root@1.com")
                .password(passwordEncoder.encode("123456"))
                .roles("vip1", "vip2", "vip3");
    }
}
