package com.tan.springbootshiro.bean;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @author tanbo
 * @date 2020/4/27 10:05
 */
@Data
@Entity
public class Permissions {

    private String id;
    private String permissionsName;

    public Permissions(String id, String permissionsName) {
        this.id = id;
        this.permissionsName = permissionsName;
    }
}
