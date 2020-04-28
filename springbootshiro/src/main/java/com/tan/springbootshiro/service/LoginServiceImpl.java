package com.tan.springbootshiro.service;

import com.tan.springbootshiro.bean.Permissions;
import com.tan.springbootshiro.bean.Role;
import com.tan.springbootshiro.bean.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author tanbo
 * @date 2020/4/27 10:08
 */
@Service
public class LoginServiceImpl implements LoginService {


    @Override
    public User getUserByName(String getMapByName) {
        return getMapByName(getMapByName);
    }

    private User getMapByName(String userName){
        //共添加两个用户，两个用户都是admin一个角色，
        //wsl有query和add权限，zhangsan只有一个query权限
        Permissions permissions1 = new Permissions("1","query");
        Permissions permissions2 = new Permissions("2","add");

        Set<Permissions> permissionsSet = new HashSet<>();
        permissionsSet.add(permissions1);
        permissionsSet.add(permissions2);

        /*角色 admin有2个权限：查询，增加*/
        Role role = new Role("1","admin",permissionsSet);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        /*用户 wsl用户有多个角色*/
        User user = new User("1","wsl","123456",roleSet);
        Map<String ,User> map = new HashMap<>();
        map.put(user.getUsername(), user);

        Permissions permissions3 = new Permissions("3","query");
        Set<Permissions> permissionsSet1 = new HashSet<>();
        permissionsSet1.add(permissions3);

        Role role1 = new Role("2","user",permissionsSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);

        /*用户 zhangsan用户有user这个角色和查询的权限*/
        User user1 = new User("2","zhangsan","123456",roleSet1);
        map.put(user1.getUsername(), user1);

        return map.get(userName);
    }
}
