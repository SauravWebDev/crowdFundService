package com.main.serv.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UserRolesMapping")
public class Roles {
    @EmbeddedId
    private RoleUserMappingKey key;
}
