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
import java.security.Principal;
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
    public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask, BindingResult result, @PathVariable(name = "project_identifier") String project_identifier, Principal principal){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null){
            return errorMap;
        }
        ProjectTask projectTask1 = projectTaskServiceImpl.addProjectTask(project_identifier, projectTask, principal.getName());
        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.OK);
    }

    @GetMapping("/{project_identifier}")
    public ResponseEntity<List<ProjectTask>> getProjectBacklog(@PathVariable(name = "project_identifier") String project_identifier, Principal principal){
        List<ProjectTask> projectTasks = projectTaskServiceImpl.findBacklogById(project_identifier, principal.getName());
        return new ResponseEntity<>(projectTasks, HttpStatus.OK);
    }

    @GetMapping("/{backlog_id}/{pt_sequence}")
    public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String pt_sequence, Principal principal){
        ProjectTask projectTask = projectTaskServiceImpl.findProjectTaskByProjectSequence(backlog_id, pt_sequence, principal.getName());
        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
    }

    @PatchMapping("/{backlog_id}/{pt_sequence}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult bindingResult, @PathVariable String backlog_id, @PathVariable String pt_sequence, Principal principal){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if (errorMap != null){
            return errorMap;
        }
        ProjectTask updatedProjectTask = projectTaskServiceImpl.updateByProjectSequence(projectTask, backlog_id, pt_sequence, principal.getName());
        return new ResponseEntity<ProjectTask>(updatedProjectTask, HttpStatus.OK);
    }

    @DeleteMapping("/{backlog_id}/{pt_sequence}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String pt_sequence, Principal principal){
        projectTaskServiceImpl.deleteProjectTaskByProjectSequence(backlog_id, pt_sequence, principal.getName());
        return new ResponseEntity<String>("Project Task "+ pt_sequence +" was deleted successfully", HttpStatus.OK);
    }
}
