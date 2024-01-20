package com.main.serv.service;


import com.main.serv.dtos.ProjectAddDto;
import com.main.serv.dtos.ProjectFilterQuery;
import com.main.serv.dtos.response.ProjectAddResponse;
import com.main.serv.dtos.response.ProjectListResponse;
import com.main.serv.dtos.response.SingleProjectResponse;
import com.main.serv.entity.Project;
import com.main.serv.repository.DonationRepository;
import com.main.serv.repository.ProjectRepository;
import com.main.serv.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceImplTest {

    @InjectMocks
    ProjectServiceImpl projectService;

    @Mock
    ProjectRepository projectRepository;

    @Mock
    DonationRepository donationRepository;

    @Test
    public void testGetSingleProject(){
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
        when(projectRepository.findById(1)).thenReturn(java.util.Optional.of(p));
        when(donationRepository.getProjectByTotalAmount(1)).thenReturn(100);
        when(donationRepository.getDonorsByProjectId(1)).thenReturn(2);
        SingleProjectResponse singleProjectResponse = projectService.getSingleProject(1);
        assertThat(singleProjectResponse).isNotNull();
    }

    @Test
    public void testCreateProject(){
       when(projectRepository.existsByTitle("title")).thenReturn(false);
        ProjectAddDto projectAddDto = new ProjectAddDto();
        projectAddDto.setTitle("title");
        projectAddDto.setOwner("abc");
        projectAddDto.setDescription("description");
        projectAddDto.setDonationEndDate(101L);
        projectAddDto.setAmountGoal(300);
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
        when(projectRepository.save(any(Project.class))).thenReturn(p);
        when(projectRepository.findByTitle("title")).thenReturn(java.util.Optional.of(p));
        ProjectAddResponse projectAddResponse = projectService.createProject(projectAddDto);
        assertThat(projectAddResponse).isNotNull();
    }

    @Test
    public void testGetAllProjectsByFilter_whenOwnerIdIsNull(){
        ProjectFilterQuery projectFilterQuery = new ProjectFilterQuery();
        projectFilterQuery.setRankingBy("AMOUNT_GOAL");
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
        when(projectRepository.getAllProjects("amount_goal")).thenReturn(projects);
        ProjectListResponse projectListResponse = projectService.getAllProjectsByFilter(projectFilterQuery);
        assertThat(projectListResponse).isNotNull();
    }
    @Test
    public void testGetAllProjectsByFilter_whenOwnerIdIsNotNull(){
        ProjectFilterQuery projectFilterQuery = new ProjectFilterQuery();
        projectFilterQuery.setRankingBy("AMOUNT_GOAL");
        projectFilterQuery.setOwnerId("1");
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
        when(projectRepository.getProjectsByOwnerId("1")).thenReturn(projects);
        ProjectListResponse projectListResponse = projectService.getAllProjectsByFilter(projectFilterQuery);
        assertThat(projectListResponse).isNotNull();
    }


}
