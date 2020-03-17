package com.example.api.service;

import com.example.api.exception.ProjectIdException;
import com.example.api.exception.ProjectNotFoundException;
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
        if (project.getId() != null){
            Project existingProject = projectRepository.findByProjectIdentifier(projectId);

            if (existingProject != null && (!existingProject.getProjectLeader().equals(username))){
                throw new ProjectNotFoundException(username + " does not have project of ID : " + projectId);
            }else if (existingProject == null){
                throw new ProjectNotFoundException("Project with ID : '"+ projectId+"' cannot be updated because it does not exists");
            }
        }
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

    public Project getByProjectId(String projectId, String username){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null){
            throw new ProjectIdException("Project ID '" + projectId+ "' does not exists");
        }

        if (!project.getProjectLeader().equals(username)){
            throw new ProjectNotFoundException(username + " does not have project of ID : " + projectId);
        }
        return project;
    }

    public List<Project> getAll(String username){
        return projectRepository.findByProjectLeader(username);
    }

    @Override
    public void deleteByProjectId(String projectId, String username) {
        projectRepository.delete(getByProjectId(projectId, username));
    }
}
