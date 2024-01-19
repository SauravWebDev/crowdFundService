package com.main.serv.dtos.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SingleProjectResponse {
    private String errorMsg;
    private SingleProject project;
}
