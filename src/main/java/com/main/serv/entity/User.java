package com.main.serv.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Long userId;
    @Column(name = "CountryCode", nullable = false)
    private String countryCode;
    @Column(name = "Mobile", nullable = true, unique = true)
    private String mobile;
    @Column(name = "EmailId", nullable = true, unique = true)
    private String email;
    @Column(nullable = false, name = "Password")
    private String password;
    @OneToOne(optional = true)
    @JoinColumn(name="UserId")
    private UserProfile userProfile;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="UserId")
    private Set<Roles> roles;

}
