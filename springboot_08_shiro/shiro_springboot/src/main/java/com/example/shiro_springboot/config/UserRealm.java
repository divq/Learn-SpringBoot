package com.example.shiro_springboot.config;

import com.example.shiro_springboot.pojo.User;
import com.example.shiro_springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("=======执行了" + "授权doGetAuthorizationInfo" + "========");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        String perms = currentUser.getPerms();
        info.addStringPermission(currentUser.getPerms());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("=======执行了" + "认证doGetAuthorizationInfo" + "========");


        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userService.queryUserByName(token.getUsername());
        if (user == null) {
            return null;// null自动表示UnknownAccountException
        }
        //密码认证shiro自动帮你做了，不用你来写。
        return new SimpleAuthenticationInfo(user, user.getPwd(), "");

    }


}
