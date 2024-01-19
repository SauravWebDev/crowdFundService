package com.main.serv.controller;

import com.main.serv.dtos.ProjectAddDto;
import com.main.serv.dtos.ProjectFilterQuery;
import com.main.serv.dtos.response.ProjectAddResponse;
import com.main.serv.dtos.response.ProjectListResponse;
import com.main.serv.dtos.response.SingleProjectResponse;
import com.main.serv.entity.Project;
import com.main.serv.exception.ApiException;
import com.main.serv.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private ProjectService projectService;
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping(value = {"/"})
    public ResponseEntity<ProjectAddResponse> add(@Valid @RequestBody ProjectAddDto projectAddDto) {
        try {
            return ResponseEntity.ok(projectService.createProject(projectAddDto));
        }catch (ApiException e) {
            ProjectAddResponse projectAddResponse = new ProjectAddResponse();
            projectAddResponse.setErrorMsg(e.getMessage());
            return new ResponseEntity<>(projectAddResponse, e.getStatus());
        }
    }
    /*
    * TODO- For now ownerId are passing as query parameter later it will extract from JWT access token
    * */
    @GetMapping(path = "/_search")
    public ResponseEntity<ProjectListResponse> getListOfProjects(@RequestParam(required=false) Map<String,String> qparams) {
        try {
            ProjectFilterQuery query = new ProjectFilterQuery();
            if(qparams.containsKey("ownerId")) {
                query.setOwnerId(qparams.get("ownerId"));
            }
            if(qparams.containsKey("rankingBy")) {
                query.setRankingBy(qparams.get("rankingBy"));
            }
            return ResponseEntity.ok(projectService.getAllProjectsByFilter(query));
        }catch (ApiException e) {
            ProjectListResponse res = new ProjectListResponse();
            res.setErrorMsg(e.getMessage());
            return new ResponseEntity<>(res, e.getStatus());
        }
    }
    @GetMapping(path = "/donation/{projectId}")
    public ResponseEntity<SingleProjectResponse> getProjectDonation(@PathVariable int projectId) {
        try {
            return ResponseEntity.ok(projectService.getSingleProject(projectId));
        }catch (ApiException e) {
            SingleProjectResponse singleProjectResponse = new SingleProjectResponse();
            singleProjectResponse.setErrorMsg(e.getMessage());
            return new ResponseEntity<>(singleProjectResponse, e.getStatus());
        }
    }
    @GetMapping(path = "/{projectId}")
    public ResponseEntity<SingleProjectResponse> getSingleProject(@PathVariable int projectId) {
        try {
            return ResponseEntity.ok(projectService.getSingleProject(projectId));
        }catch (ApiException e) {
            SingleProjectResponse singleProjectResponse = new SingleProjectResponse();
            singleProjectResponse.setErrorMsg(e.getMessage());
            return new ResponseEntity<>(singleProjectResponse, e.getStatus());
        }
    }


}
