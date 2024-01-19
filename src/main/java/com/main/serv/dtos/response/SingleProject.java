package com.main.serv.dtos.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SingleProject {
    private int id;
    private String title;
    private String description;
    private String owner;
    private int amountGoal;
    private int amountRaised;
    private int donationEndDate;
    private int donationCount;
    private int createdAt;
    private int updatedAt;
    private String status;
}
