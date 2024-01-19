package com.main.serv.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "owner", nullable = false)
    private String owner;
    @Column(name = "amount_goal", nullable = false)
    private int amountGoal;
    @Column(name = "amount_raised")
    private int amountRaised;
    @Column(name = "status")
    private String status;
    @Column(name = "donation_end_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date donationEndDate;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
