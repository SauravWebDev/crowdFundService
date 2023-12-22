package com.main.serv.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userprofile")
public class UserProfile {
    @Id
    @Column(name = "UserId")
    private String userId;
    @Column(name = "Username", nullable = true, unique = true)
    private String userName;
    @Column(name = "FirstName", nullable = false)
    private String firstName;
    @Column(name = "MiddleName")
    private String middleName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "DOB")
    private String dob;
    @Column(name = "Gender")
    private String gender;
    @Column(name = "City")
    private String city;
    @Column(name = "State")
    private String state;
    @Column(name = "Country")
    private String country;
    @Column(name = "Zip")
    private String zip;
}
