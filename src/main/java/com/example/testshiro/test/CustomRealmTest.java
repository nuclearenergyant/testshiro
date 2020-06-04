package com.example.testshiro.test;

import com.example.testshiro.Realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: weige
 * \* Date: 2020/6/3
 * \* Time: 23:59
 * \* Description: www.diandian.在线
 * \* 功能说明：
 * \
 */
public class CustomRealmTest {

    @Test
    public void test(){
        CustomRealm customRealm=new CustomRealm();
        //创建SecurityManager
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        securityManager.setRealm(customRealm);

        //主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin","admin");

        subject.login(token);
        subject.checkRole("user");
        subject.checkPermission("add");
        System.out.println("是否认证"+subject.isAuthenticated());

    }
}