package com.tan.springbootshiro.bean;

import lombok.Data;


import javax.persistence.Entity;

import java.util.Set;

/**
 * @author tanbo
 * @date 2020/4/27 9:31
 */
@Data
@Entity
public class Role {


    private String id;

    private String roleName;

    /*角色对应的权限*/
    private Set<Permissions> permissions;

    public Role(String id, String roleName, Set<Permissions> permissions) {
        this.id = id;
        this.roleName = roleName;
        this.permissions = permissions;
    }
}
