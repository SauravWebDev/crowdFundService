package com.main.serv.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignupDto {
    @NotBlank(message = "The Mobile is required.")
    private String mobile;
    @NotBlank(message = "The Email is required.")
    private String emailId;
    @NotBlank(message = "The Password is required.")
    private String password;
    @NotBlank(message = "The Country Code is required.")
    @NotNull(message = "The Country Code is required.")
    private String countryCode;

}
