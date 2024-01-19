package com.main.serv.service.impl;

import com.main.serv.dtos.ProjectAddDto;
import com.main.serv.dtos.ProjectFilterQuery;
import com.main.serv.dtos.response.ProjectAddResponse;
import com.main.serv.dtos.response.ProjectListResponse;
import com.main.serv.dtos.response.SingleProject;
import com.main.serv.dtos.response.SingleProjectResponse;
import com.main.serv.entity.Project;
import com.main.serv.exception.ApiException;
import com.main.serv.repository.DonationRepository;
import com.main.serv.repository.ProjectRepository;
import com.main.serv.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final DonationRepository donationRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, DonationRepository donationRepository) {
        this.projectRepository = projectRepository;
        this.donationRepository = donationRepository;
    }
    @Override
    public ProjectAddResponse createProject(ProjectAddDto project) {
        if(projectRepository.existsByTitle(project.getTitle())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Title already taken");
        }
        Project projectEntity = getProject(project);
        projectRepository.save(projectEntity);
        int projectId = projectRepository.findByTitle(project.getTitle()).get().getId();
        ProjectAddResponse res = new ProjectAddResponse();
        res.setMsg("Project created successfully");
        res.setProjectId(projectId);
        return res;
    }

    @Override
    public ProjectListResponse getAllProjectsByFilter(ProjectFilterQuery query) {
        ProjectListResponse projectListResponse = new ProjectListResponse();
        String orderBy = "created_at";
        if(query.getRankingBy() == "AMOUNT_GOAL") {
            orderBy = "amount_goal";
        } else if(query.getRankingBy() == "DONATION_END_DATE") {
            orderBy = "donation_end_date";
        }
        projectListResponse.setProjects(
                query.getOwnerId() == null ? projectRepository.getAllProjects(orderBy)
                : projectRepository.getProjectsByOwnerId(query.getOwnerId())
                );
        return projectListResponse;
    }

    private static Project getProject(ProjectAddDto project) {
        Project projectEntity = new Project();
        projectEntity.setTitle(project.getTitle());
        projectEntity.setOwner(project.getOwner());
        projectEntity.setDescription(project.getDescription());
        projectEntity.setStatus("ACTIVE");
        projectEntity.setAmountGoal(project.getAmountGoal());
        projectEntity.setAmountRaised(0);
        Timestamp stamp = new Timestamp(project.getDonationEndDate() * 1000);
        Date date = new Date(stamp.getTime());
        projectEntity.setDonationEndDate(date);
        return projectEntity;
    }

    @Override
    public SingleProjectResponse getSingleProject(int projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if(project.isEmpty()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid Project Id");
        }else {
            SingleProjectResponse projectRes = new SingleProjectResponse();
            SingleProject singleProject = new SingleProject();
            Integer donationSum = donationRepository.getProjectByTotalAmount(projectId);
            int goalAmount = donationSum == null ? 0 : donationSum;
            Integer donorsCount = donationRepository.getDonorsByProjectId(projectId);
            int noOfDonors = donorsCount == null ? 0 : donorsCount;
            singleProject.setId(projectId);
            singleProject.setDescription(project.get().getDescription());
            singleProject.setTitle(project.get().getTitle());
            singleProject.setOwner(project.get().getOwner());
            singleProject.setAmountRaised(goalAmount);
            singleProject.setDonationCount(noOfDonors);
            singleProject.setCreatedAt(project.get().getCreatedAt().getSeconds() * 1000);
            singleProject.setStatus(project.get().getStatus());
            projectRes.setProject(singleProject);
            return projectRes;
        }
    }
}
