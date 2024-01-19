package com.main.serv.dtos.response;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddDonationResponse {
    private String errorMsg;
    private String msg;
    private int donationId;
}
