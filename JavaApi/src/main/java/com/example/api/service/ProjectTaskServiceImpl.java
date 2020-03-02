package com.example.api.service;

import com.example.api.exception.ProjectIdException;
import com.example.api.model.Backlog;
import com.example.api.model.ProjectTask;
import com.example.api.repository.BacklogRepository;
import com.example.api.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService {

    private BacklogRepository backlogRepository;
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    public ProjectTaskServiceImpl(BacklogRepository backlogRepository, ProjectTaskRepository projectTaskRepository){
        this.backlogRepository = backlogRepository;
        this.projectTaskRepository = projectTaskRepository;
    }

    @Override
    public ProjectTask addProjectTask(String projectId, ProjectTask projectTask) {
        // get the project id from the backlog
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectId);
        // set the project task backlog
        projectTask.setBacklog(backlog);
        // get the project task sequence from the backlog and increment by one
        Integer backlogSequence = backlog.getPTSequence();
        ++backlogSequence;
        // set back the project task sequence after increment to get the updated project task sequence
        backlog.setPTSequence(backlogSequence);
        // set the project task sequence with the incremented backlog sequence
        projectTask.setProjectSequence(projectId+ "-" +backlogSequence);
        //set the project id
        projectTask.setProjectIdentifier(projectId);
        // set the priority to 3 only if the priority is 0 or null
        if (projectTask.getPriority() == 0){
            projectTask.setPriority(3);
        }
        // set the status to "to_do" only if it is empty or null
        if (projectTask.getStatus() == "" || projectTask.getStatus() == null){
            projectTask.setStatus("TO_DO");
        }
        // save the project task
        return projectTaskRepository.save(projectTask);
    }

    @Override
    public List<ProjectTask> findBacklogById(String projectId) {
        try{
            return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectId);
        }catch (Exception e) {
            throw new ProjectIdException("Project ID '" + projectId + "' does not exists in backlog");
        }
    }
}
