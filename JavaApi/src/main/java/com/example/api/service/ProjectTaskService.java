package com.example.api.service;

import com.example.api.model.Project;
import com.example.api.model.ProjectTask;

import java.util.List;

public interface ProjectTaskService {
    ProjectTask addProjectTask(String projectId, ProjectTask projectTask);
    ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_sequence);
    List<ProjectTask> findBacklogById(String projectId);
    ProjectTask findProjectTaskByProjectSequence(String backlog_id, String pt_sequence);
    void deleteProjectTaskByProjectSequence(String backlog_id, String pt_sequence);
}
