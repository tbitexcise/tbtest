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
public class User {


    private String id;

    private String username;

    private String password;

    private Set<Role> roles;

    public User(String id, String username, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
