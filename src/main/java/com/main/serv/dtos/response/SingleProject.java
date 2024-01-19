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
    private long donationEndDate;
    private int donationCount;
    private long createdAt;
    private long updatedAt;
    private String status;
}
