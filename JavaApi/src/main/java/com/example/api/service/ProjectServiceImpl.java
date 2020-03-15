package com.example.api.service;

import com.example.api.exception.ProjectIdException;
import com.example.api.model.Backlog;
import com.example.api.model.Project;
import com.example.api.model.User;
import com.example.api.repository.BacklogRepository;
import com.example.api.repository.ProjectRepository;
import com.example.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private BacklogRepository backlogRepository;
    private UserRepository userRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, BacklogRepository backlogRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.backlogRepository = backlogRepository;
        this.userRepository = userRepository;
    }

    public Project createAndUpdate(Project project, String username){
        String projectId = (project.getProjectIdentifier().toUpperCase());
        try{
            User user = userRepository.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());

            project.setProjectIdentifier(projectId);
            if (project.getId() == null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(projectId);
            }
            if (project.getId() != null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(projectId));
            }
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID '" + projectId + "' already exists");
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
