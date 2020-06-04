package com.example.testshiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: weige
 * \* Date: 2020/6/3
 * \* Time: 16:10
 * \* Description: www.diandian.在线
 * \* 功能说明：
 * \
 */
public class AuthenticationTest {

    SimpleAccountRealm accountRealm=new SimpleAccountRealm();

    @Before
    public void addUser(){
        accountRealm.addAccount("admin","123456","admin","user");
    }

    @Test
    public void test(){
        //创建SecurityManager
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        securityManager.setRealm(accountRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        //提交主体认证
        UsernamePasswordToken token = new UsernamePasswordToken("admin","123456");
        //主体提交认证请求
        subject.login(token);
        System.out.println("是否认证"+subject.isAuthenticated());

        //验证用户是否该角色
        subject.checkRoles("user","admin");
        subject.logout();
        System.out.println("是否认证"+subject.isAuthenticated());
    }


}