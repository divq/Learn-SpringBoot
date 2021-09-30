package com.example.springboot_04_data.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean(name = "dataSource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    //后台监控,相当于web.xml
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //后台需要有人登陆, 账号密码的配置
        HashMap<String, String> initialParameters = new HashMap<>();
        initialParameters.put("loginUsername", "root");//注意用户名和密码这两个key的名字是固定的
        initialParameters.put("loginPassword", "123");


        //允许谁可以访问
//        initialParameters.put("allow", "127.0.0.1");//后面那个如果不写就是所与人都可以访问

        //禁止谁可以访问
//        initialParameters.put("xxx", "192.168.2.2");

        //增加配置
        bean.setInitParameters(initialParameters);
        return bean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);

        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }


}
