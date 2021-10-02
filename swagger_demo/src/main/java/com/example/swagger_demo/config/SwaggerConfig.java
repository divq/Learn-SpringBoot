package com.example.swagger_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docketA() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket docket(Environment environment) {
        Profiles profiles = Profiles.of("dev");
        boolean isDev = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(isDev)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger_demo.controller"))
                .build();
    }

    //配置swagger文档里面的一些信息
    private ApiInfo apiInfo() {
        return new ApiInfo("我的Swagger文档",
                "这里是描述",
                "版本1.0",
                "127.0.0.1",
                new Contact("tim", "www.baidu.com", "tim@apple.com"),
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }
}
