package com.example.api.service;

import com.example.api.exception.ProjectIdException;
import com.example.api.exception.ProjectNotFoundException;
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
        try{
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
            if (projectTask.getStatus().equals("") || projectTask.getStatus() == null){
                projectTask.setStatus("TO_DO");
            }
            // save the project task
            return projectTaskRepository.save(projectTask);
        }catch (Exception e){
            throw new ProjectNotFoundException("Project not found");
        }

    }

    @Override
    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_sequence) {
        ProjectTask projectTask = findProjectTaskByProjectSequence(backlog_id, pt_sequence);
        projectTask = updatedTask;
        return projectTaskRepository.save(projectTask);
    }

    @Override
    public List<ProjectTask> findBacklogById(String projectId) {
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectId);
        if (backlog != null){
            return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectId);
        }
        throw new ProjectNotFoundException("Project ID '" + projectId + "' does not exists in backlog");
    }

    @Override
    public ProjectTask findProjectTaskByProjectSequence(String backlog_id, String pt_sequence) {
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if (backlog == null){
            throw new ProjectNotFoundException("Project with ID: '"+ backlog_id +"' not found");
        }

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_sequence);
        if (projectTask == null){
            throw new ProjectNotFoundException("Project Task: '"+ pt_sequence +"' not found");
        }

        if (!projectTask.getProjectIdentifier().equals(backlog_id.toUpperCase())){
            throw new ProjectNotFoundException("Project Task: '"+ pt_sequence +"' does not exist in project: '"+ backlog_id);
        }
        return projectTask;
    }

    @Override
    public void deleteProjectTaskByProjectSequence(String backlog_id, String pt_sequence) {
        ProjectTask projectTask = findProjectTaskByProjectSequence(backlog_id, pt_sequence);

//        Backlog backlog = projectTask.getBacklog();
//        List<ProjectTask> projectTasks = backlog.getProjectTask();
//        projectTasks.remove(projectTask);
//        backlogRepository.save(backlog);
        projectTaskRepository.delete(projectTask);
    }


}
