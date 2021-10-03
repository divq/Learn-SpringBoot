package com.example.shiro_springboot.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //过滤器分为两组，一组是认证过滤器，有anon，authcBasic，auchc，user，一组是授权过滤器，有perms，roles，ssl，rest，port
//        anon(org.apache.shiro.web.filter.authc.AnonymousFilter):例子/admins/**=anon 没有参数，表示可以匿名使用。
//         authc(org.apache.shiro.web.filter.authc.FormAuthenticationFilter):例如/admins/user/**=authc表示需要认证(登录)才能使用，没有参数
//         authcBasic(org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter):例如/admins/user/**=authcBasic,没有参数表示httpBasic认证
//         perms org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter:（权限）例子/admins/user/=perms[user:add:],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，例如/admins/user/=perms[“user:add:,user:modify:*”]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
//         port org.apache.shiro.web.filter.authz.PortFilter:例子/admins/user/**=port[8081],当请求的url的端口不是8081是跳转到schemal://serverName:8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString
//         rest org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter:例子/admins/user/=rest[user],根据请求的方法，相当于/admins/user/=perms[user:method] ,其中method为post，get，delete等。
//         roles org.apache.shiro.web.filter.authz.RolesAuthorizationFilter:(角色)例子/admins/user/=roles[admin],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，例如admins/user/=roles[“admin,guest”],每个参数通过才算通过，相当于hasAllRoles()方法。
//         ssl org.apache.shiro.web.filter.authz.SslFilter:例子/admins/user/**=ssl没有参数，表示安全的url请求，协议为https
//         user org.apache.shiro.web.filter.authc.UserFilter:例如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查


        filterChainDefinitionMap.put("/addPage", "perms[user:add]");
        filterChainDefinitionMap.put("/updatePage", "perms[user:update]");
        //这样使用通配符：
//        filterChainDefinitionMap.put("/user/*", "authc");


        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        factoryBean.setLoginUrl("/loginPage");
        factoryBean.setUnauthorizedUrl("/noAuth");

        return factoryBean;
    }


    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    //整合ShiroDialect，用来整合shiro thymeleaf
    @Bean(name = "shiroDialect")
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
