package com.main.serv.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddDonationRequest {
    @NotNull(message = "The amount is required.")
    private int amount;
    @NotNull(message = "The userName is required.")
    private String userName;
    @NotNull(message = "The projectId is required.")
    private int projectId;
    private String userDocument;
    private String details;
}
