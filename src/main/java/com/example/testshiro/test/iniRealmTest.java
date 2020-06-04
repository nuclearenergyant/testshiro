package com.example.testshiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: weige
 * \* Date: 2020/6/3
 * \* Time: 16:38
 * \* Description: www.diandian.在线
 * \* 功能说明：
 * \
 */
public class iniRealmTest {
    @Test
    public void test(){
        IniRealm iniRealm=new IniRealm("classpath:user.ini");
        //创建SecurityManager
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        securityManager.setRealm(iniRealm);

        //主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin","123456");

        subject.login(token);
        subject.checkRole("admin");
        subject.checkPermission("update");
        System.out.println("是否认证"+subject.isAuthenticated());

    }
}