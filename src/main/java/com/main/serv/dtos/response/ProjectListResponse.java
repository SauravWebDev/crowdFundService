package com.main.serv.dtos.response;

import com.main.serv.entity.Project;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectListResponse {
    private String errorMsg;
    private List<Project> projects;
}
