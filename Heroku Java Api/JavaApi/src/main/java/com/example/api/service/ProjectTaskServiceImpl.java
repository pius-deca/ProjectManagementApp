package com.example.api.service;

import com.example.api.exception.ProjectIdException;
import com.example.api.exception.ProjectNotFoundException;
import com.example.api.model.Backlog;
import com.example.api.model.ProjectTask;
import com.example.api.repository.BacklogRepository;
import com.example.api.repository.ProjectRepository;
import com.example.api.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService {

    private BacklogRepository backlogRepository;
    private ProjectTaskRepository projectTaskRepository;
    private ProjectRepository projectRepository;
    private ProjectServiceImpl projectServiceImpl;

    @Autowired
    public ProjectTaskServiceImpl(BacklogRepository backlogRepository, ProjectTaskRepository projectTaskRepository, ProjectRepository projectRepository, ProjectServiceImpl projectServiceImpl){
        this.backlogRepository = backlogRepository;
        this.projectTaskRepository = projectTaskRepository;
        this.projectRepository = projectRepository;
        this.projectServiceImpl = projectServiceImpl;
    }

    @Override
    public ProjectTask addProjectTask(String projectId, ProjectTask projectTask, String username) {
        // get the project id from the backlog
        Backlog backlog = projectServiceImpl.getByProjectId(projectId, username).getBacklog();
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
        if (projectTask.getPriority() == null || projectTask.getPriority() == 0){
            projectTask.setPriority(3);
        }
        // set the status to "to_do" only if it is empty or null
        if ( projectTask.getStatus() == null || projectTask.getStatus().equals("")){
            projectTask.setStatus("TO_DO");
        }
        // save the project task
        return projectTaskRepository.save(projectTask);
    }

    @Override
    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_sequence, String username) {
        ProjectTask projectTask = findProjectTaskByProjectSequence(backlog_id, pt_sequence, username);
        projectTask = updatedTask;

        return projectTaskRepository.save(projectTask);
    }

    @Override
    public List<ProjectTask> findBacklogById(String projectId, String username) {
        projectServiceImpl.getByProjectId(projectId, username);
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectId);
    }

    @Override
    public ProjectTask findProjectTaskByProjectSequence(String backlog_id, String pt_sequence, String username) {
        projectServiceImpl.getByProjectId(backlog_id, username);

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_sequence);
        if (projectTask == null){
            throw new ProjectNotFoundException("Project Task: '"+ pt_sequence +"' not found");
        }
        if (!projectTask.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectNotFoundException("Project Task: '"+ pt_sequence +"' does not exist in project: '"+ backlog_id);
        }
        return projectTask;
    }

    @Override
    public void deleteProjectTaskByProjectSequence(String backlog_id, String pt_sequence, String username) {
        ProjectTask projectTask = findProjectTaskByProjectSequence(backlog_id, pt_sequence, username);

//        Backlog backlog = projectTask.getBacklog();
//        List<ProjectTask> projectTasks = backlog.getProjectTask();
//        projectTasks.remove(projectTask);
//        backlogRepository.save(backlog);
        projectTaskRepository.delete(projectTask);
    }


}
