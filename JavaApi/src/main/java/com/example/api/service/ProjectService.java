package com.example.api.service;

import com.example.api.model.Project;

import java.util.List;

public interface ProjectService {
    public Project createAndUpdate(Project project, String username);
    public Project getByProjectId(String projectId);
    public List<Project> getAll();
    public void deleteByProjectId(String projectId);
}
