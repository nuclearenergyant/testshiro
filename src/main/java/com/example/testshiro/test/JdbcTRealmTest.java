package com.example.testshiro.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: weige
 * \* Date: 2020/6/3
 * \* Time: 16:54
 * \* Description: www.diandian.在线
 * \* 功能说明：
 * \
 */
public class JdbcTRealmTest {
    DruidDataSource druidDataSource=new DruidDataSource();
    {
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test02");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("");
    }
    @Test
    public void test(){
        JdbcRealm jdbcRealm=new JdbcRealm();
        jdbcRealm.setDataSource(druidDataSource);
        //特别注意
        jdbcRealm.setPermissionsLookupEnabled(true);

        jdbcRealm.setAuthenticationQuery("select password from testuser where username = ?");
//        jdbcRealm.setPermissionsQuery();
//        jdbcRealm.setUserRolesQuery();
        //创建SecurityManager
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        securityManager.setRealm(jdbcRealm);

        //主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin","123456");

        subject.login(token);
        subject.checkRole("user");
        subject.checkPermission("add");
        System.out.println("是否认证"+subject.isAuthenticated());

    }
}