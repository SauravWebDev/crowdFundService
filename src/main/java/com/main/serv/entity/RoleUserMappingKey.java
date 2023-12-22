package com.main.serv.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class RoleUserMappingKey implements Serializable {
    @Column(name = "UserId")
    private Long userId;
    @Column(name = "RoleName")
    private String roleName;
}
