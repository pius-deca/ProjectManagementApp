package com.example.api.repository;

import com.example.api.model.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Integer> {
    List<ProjectTask> findByProjectIdentifierOrderByPriority(String projectId);
    ProjectTask findByProjectSequence(String sequence);
}
