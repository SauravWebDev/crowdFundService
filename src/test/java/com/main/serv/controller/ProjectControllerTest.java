package com.main.serv.controller;


import com.main.serv.dtos.ProjectAddDto;
import com.main.serv.dtos.response.ProjectAddResponse;
import com.main.serv.dtos.response.ProjectListResponse;
import com.main.serv.dtos.response.SingleProject;
import com.main.serv.dtos.response.SingleProjectResponse;
import com.main.serv.entity.Project;
import com.main.serv.exception.ApiException;
import com.main.serv.service.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {


    @InjectMocks
    ProjectController projectController;

    @Mock
    ProjectService projectService;

    @Test
    public void testAdd(){
        ProjectAddResponse res = new ProjectAddResponse();
        res.setMsg("Project created successfully");
        res.setProjectId(1);
        ProjectAddDto projectAddDto = new ProjectAddDto();
        projectAddDto.setTitle("title");
        projectAddDto.setAmountGoal(100);
        projectAddDto.setOwner("owner");
        projectAddDto.setDescription("desc");
        projectAddDto.setDonationEndDate(101L);
        when(projectService.createProject(projectAddDto)).thenReturn(res);
        ResponseEntity<ProjectAddResponse> projectAddResponse = projectController.add(projectAddDto);
        Assertions.assertEquals(HttpStatus.OK,projectAddResponse.getStatusCode());
    }

    @Test
    public void testAddWhenTitleIsNull(){
        ProjectAddDto projectAddDto = new ProjectAddDto();
        projectAddDto.setTitle(null);
        projectAddDto.setAmountGoal(100);
        projectAddDto.setOwner("owner");
        projectAddDto.setDescription("desc");
        projectAddDto.setDonationEndDate(101L);
        ProjectAddResponse projectAddResponse = new ProjectAddResponse();
        projectAddResponse.setErrorMsg("The title is required.");
        when(projectService.createProject(projectAddDto)).thenThrow(new ApiException(HttpStatus.BAD_REQUEST,projectAddResponse.getErrorMsg()));
        ResponseEntity<ProjectAddResponse> pres = projectController.add(projectAddDto);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,pres.getStatusCode());
    }

    @Test
    public void testGetSingleProject(){
        SingleProject singleProject = new SingleProject();
        singleProject.setDescription("desc");
        singleProject.setAmountGoal(100);
        singleProject.setTitle("title1");
        singleProject.setOwner("owner1");
        singleProject.setCreatedAt(101L);
        singleProject.setId(1);
        singleProject.setUpdatedAt(102L);
        singleProject.setStatus("ACTIVE");
        singleProject.setDonationCount(2);
        singleProject.setAmountRaised(50);
        singleProject.setDonationEndDate(105L);
        SingleProjectResponse res = new SingleProjectResponse();
        res.setProject(singleProject);

        when(projectService.getSingleProject(1)).thenReturn(res);
        ResponseEntity<SingleProjectResponse> singleProjectRes = projectController.getSingleProject(1);
        Assertions.assertEquals(HttpStatus.OK,singleProjectRes.getStatusCode());
    }
    @Test
    public void testGetSingleProject_WhenOwnerIdIsNull(){
        SingleProjectResponse res = new SingleProjectResponse();
        res.setErrorMsg("Owner id is null");
        when(projectService.getSingleProject(1)).thenThrow(new ApiException(HttpStatus.BAD_REQUEST, res.getErrorMsg()));
        ResponseEntity<SingleProjectResponse> singleProjectRes = projectController.getSingleProject(1);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,singleProjectRes.getStatusCode());
    }
    @Test
    public void testListOfProjectWhenQueryIsOwnerId(){
        Project p = new Project();
        p.setStatus("ACTIVE");
        p.setAmountRaised(200);
        p.setAmountGoal(300);
        p.setOwner("abc");
        p.setTitle("title");
        p.setId(1);
        Date d1 = new Date();
        p.setCreatedAt(d1);
        p.setUpdatedAt(d1);
        p.setDonationEndDate(d1);
        Project p1 = new Project();
        p1.setStatus("ACTIVE");
        p1.setAmountRaised(100);
        p1.setAmountGoal(200);
        p1.setOwner("abc1");
        p1.setTitle("title1");
        p1.setId(2);
        Date d2 = new Date();
        p1.setCreatedAt(d2);
        p1.setUpdatedAt(d2);
        p1.setDonationEndDate(d2);
        List<Project> projects = new ArrayList<>();
        projects.add(p);
        projects.add(p1);
        Map<String,String> qparams = new HashMap<>();
        qparams.put("ownerId","owner");
        qparams.put("rankingBy","rankingBy");
        ProjectListResponse projectListResponse = new ProjectListResponse();
        projectListResponse.setProjects(projects);
        when(projectService.getAllProjectsByFilter(any())).thenReturn(projectListResponse);
        ResponseEntity<ProjectListResponse> projectList= projectController.getListOfProjects(qparams);
        Assertions.assertEquals(HttpStatus.OK,projectList.getStatusCode());

    }

    @Test
    public void testListOfProjectWhenException(){
        ProjectListResponse res = new ProjectListResponse();
        res.setErrorMsg("Owner id is null");
        Map<String,String> qparams = new HashMap<>();
        qparams.put("ownerId","owner");
        qparams.put("rankingBy","rankingBy");
        when(projectService.getAllProjectsByFilter(any())).thenThrow(new ApiException(HttpStatus.BAD_REQUEST, res.getErrorMsg()));
        ResponseEntity<ProjectListResponse> listOfProjects = projectController.getListOfProjects(qparams);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,listOfProjects.getStatusCode());
    }

}
