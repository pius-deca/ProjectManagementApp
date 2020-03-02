package com.example.api.controller;

import com.example.api.model.ProjectTask;
import com.example.api.service.MapValidationErrorService;
import com.example.api.service.ProjectTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    private ProjectTaskServiceImpl projectTaskServiceImpl;
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    public BacklogController(ProjectTaskServiceImpl projectTaskServiceImpl, MapValidationErrorService mapValidationErrorService){
        this.projectTaskServiceImpl = projectTaskServiceImpl;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("/{project_identifier}")
    public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask, BindingResult result, @PathVariable(name = "project_identifier") String project_identifier){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null){
            return errorMap;
        }
        ProjectTask projectTask1 = projectTaskServiceImpl.addProjectTask(project_identifier, projectTask);
        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.OK);
    }

    @GetMapping("/{project_identifier}")
    public ResponseEntity<List<ProjectTask>> getProjectBacklog(@PathVariable(name = "project_identifier") String project_identifier){
        List<ProjectTask> projectTasks = projectTaskServiceImpl.findBacklogById(project_identifier);
        return new ResponseEntity<>(projectTasks, HttpStatus.OK);
    }

}
