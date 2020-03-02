package com.example.api.service;

import com.example.api.model.ProjectTask;

import java.util.List;

public interface ProjectTaskService {
    ProjectTask addProjectTask(String projectId, ProjectTask projectTask);
    List<ProjectTask> findBacklogById(String projectId);
}
