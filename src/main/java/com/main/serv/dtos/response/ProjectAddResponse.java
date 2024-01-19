package com.main.serv.dtos.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectAddResponse {
    private String errorMsg;
    private String msg;
    private int projectId;
}
