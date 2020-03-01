package com.example.api.service;

import com.example.api.exception.ProjectIdException;
import com.example.api.model.Project;
import com.example.api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createAndUpdate(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project getByProjectId(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project != null){
            return project;
        }
        throw new ProjectIdException("Project ID '" + projectId+ "' does not exists");
    }

    public List<Project> getAll(){
        return projectRepository.findAll();
    }

    @Override
    public void deleteByProjectId(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null){
            throw new ProjectIdException("Cannot delete Project with ID of '" + projectId + "'. This project does not exists");
        }
        projectRepository.delete(project);
    }
}
