package com.main.serv.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "user_document_id")
    private String userDocumentId;
    @Column(name = "details")
    private String details;
    @Column(name = "amount", nullable = false)
    private int amount;
    @Column(name = "status")
    private String status;
    @Column(name = "project_id", nullable = false)
    private int projectId;
    @OneToOne(optional = true)
    @JoinColumn(name="id")
    private Project project;
}
