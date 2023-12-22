package com.main.serv.dtos.response;

import com.main.serv.entity.User;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignupResponse {
    private String errorMsg;
    private String msg;
    private User user;

}
