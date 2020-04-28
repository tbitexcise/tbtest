package com.tan.springbootshiro.shiro;

import com.tan.springbootshiro.bean.Permissions;
import com.tan.springbootshiro.bean.Role;
import com.tan.springbootshiro.bean.User;
import com.tan.springbootshiro.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author tanbo
 * @date 2020/4/27 10:21
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取登录用户名
        String name = (String)principalCollection.getPrimaryPrincipal();
        //根据用户名查询用户信息
        User user = loginService.getUserByName(name);

        //添加角色，权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //得到用户指定的角色
        for (Role role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //添加权限
            for (Permissions permissions : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
            }
        }

        return simpleAuthorizationInfo;
    }

    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //post请求的时候会先认证，再执行请求
        if (authenticationToken.getPrincipal() == null){
            return null;
        }

        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        User user = loginService.getUserByName(name);

        if (user == null){
            return null;
        }else {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name,user.getPassword().toString(),getName());
            return simpleAuthenticationInfo;
        }

    }
}
