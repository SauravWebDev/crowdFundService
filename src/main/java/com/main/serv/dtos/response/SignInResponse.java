package com.main.serv.dtos.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignInResponse {
    private String errorMsg;
    private String accessToken;
    private String tokenType = "Bearer";
    private boolean isProfile;
}
