package com.example.api.service;

import com.example.api.model.Project;
import com.example.api.model.ProjectTask;

import java.util.List;

public interface ProjectTaskService {
    ProjectTask addProjectTask(String projectId, ProjectTask projectTask, String username);
    ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_sequence, String username);
    List<ProjectTask> findBacklogById(String projectId, String username);
    ProjectTask findProjectTaskByProjectSequence(String backlog_id, String pt_sequence, String username);
    void deleteProjectTaskByProjectSequence(String backlog_id, String pt_sequence, String username);
}
