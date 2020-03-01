package com.example.api.service;

import com.example.api.model.Project;

import java.util.List;

public interface ProjectService {
    public Project createAndUpdate(Project project);
    public Project getByProjectId(String projectId);
    public List<Project> getAll();
}
