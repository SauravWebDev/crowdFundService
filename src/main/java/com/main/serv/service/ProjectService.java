package com.main.serv.service;

import com.main.serv.dtos.ProjectAddDto;
import com.main.serv.dtos.ProjectFilterQuery;
import com.main.serv.dtos.response.ProjectAddResponse;
import com.main.serv.dtos.response.ProjectListResponse;
import com.main.serv.dtos.response.SingleProjectResponse;
import com.main.serv.entity.Project;

import java.util.List;

public interface ProjectService {
    ProjectAddResponse createProject(ProjectAddDto project);
    SingleProjectResponse getSingleProject(int projectId);
    ProjectListResponse getAllProjectsByFilter(ProjectFilterQuery query);
}
