package com.example.api.controller;

import com.example.api.model.Project;
import com.example.api.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/project")
@CrossOrigin
public class ProjectController {

    private ProjectServiceImpl projectServiceImpl;

    @Autowired
    public ProjectController(ProjectServiceImpl projectServiceImpl) {
        this.projectServiceImpl = projectServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Project> create(@Valid @RequestBody Project project){
        Project newPoject = projectServiceImpl.createAndUpdate(project);
        return new ResponseEntity<>(newPoject, HttpStatus.OK);
    }
}

