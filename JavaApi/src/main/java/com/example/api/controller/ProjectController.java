package com.example.api.controller;

import com.example.api.model.Project;
import com.example.api.service.MapValidationErrorService;
import com.example.api.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/project")
@CrossOrigin
public class ProjectController {

    private ProjectServiceImpl projectServiceImpl;
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectServiceImpl, MapValidationErrorService mapValidationErrorService) {
        this.projectServiceImpl = projectServiceImpl;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Project project, BindingResult result, Principal principal){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null){
            return errorMap;
        }

        Project newPoject = projectServiceImpl.createAndUpdate(project, principal.getName());
        return new ResponseEntity<Project>(newPoject, HttpStatus.CREATED);
    }

    @GetMapping("/{project_id}")
    public ResponseEntity<?> getByProjectIdentifier(@PathVariable(name = "project_id") String projectId, Principal principal){
        Project project = projectServiceImpl.getByProjectId(projectId, principal.getName());
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Project> getAllProjects(Principal principal){
        return projectServiceImpl.getAll(principal.getName());
    }

    @DeleteMapping("/{project_id}")
    public ResponseEntity<?> deleteProject(@PathVariable(name = "project_id") String projectId, Principal principal){
        projectServiceImpl.deleteByProjectId(projectId, principal.getName());
        return new ResponseEntity<String>("Project with ID of '" + projectId + "' was deleted", HttpStatus.OK);
    }
}

