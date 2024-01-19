package com.main.serv.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectAddDto {
    @NotNull(message = "The title is required.")
    private String title;
    @NotNull(message = "The owner is required.")
    private String owner;
    @NotNull(message = "The amountGoal is required.")
    private int amountGoal;
    @NotNull(message = "The donationEndDate is required.")
    private Long donationEndDate;
    private String description;
}
