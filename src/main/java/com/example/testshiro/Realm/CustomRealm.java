package com.example.testshiro.Realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: weige
 * \* Date: 2020/6/3
 * \* Time: 19:21
 * \* Description: www.diandian.在线
 * \* 功能说明：自己新建的Realm类
 * \
 */
public class CustomRealm extends AuthorizingRealm {

    //模拟数据库的设计
    Map<String,String> usermap = new HashMap<>(16);
    {
        usermap.put("admin","admin");
        //设置Realm的名称
        super.setName("customRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("开启授权");
        String username=(String)principalCollection.getPrimaryPrincipal();
        //获得角色
        Set<String> roles=getRolesByname(username);
        //获得授权
        Set<String> permissions=getpemissionsByname(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    private Set<String> getpemissionsByname(String username) {
        Set<String> set=new HashSet<>();
        set.add("del");
        set.add("update");
        set.add("add");
        return set;
    }

    private Set<String> getRolesByname(String username) {
        Set<String> set=new HashSet<>();
        set.add("user");
        set.add("admin");
        return set;
    }

    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("认证开始了");
        //通过主体传来的认证信息中，获得用户名
        String username= (String) authenticationToken.getPrincipal();
        //通过用户的到数据库中获取凭证(模拟数据库拿数据)
        String password=getpasswordByUsername(username);
        if(password==null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo
                ("admin",password,"customRealm");
        return authenticationInfo;
    }

    //模拟数据库的
    private String getpasswordByUsername(String username) {
        return usermap.get(username);
    }
}