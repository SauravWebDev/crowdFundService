package com.main.serv.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto {
    @NotBlank(message = "The Email is required.")
    private String emailId;
    @NotBlank(message = "The Password is required.")
    private String password;
}