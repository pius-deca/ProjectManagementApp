package com.example.api.repository;

import com.example.api.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Project findByProjectIdentifier(String projectId);
    List<Project> findAll();
    List<Project> findByProjectLeader(String username);
}
