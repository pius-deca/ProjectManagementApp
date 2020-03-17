package com.example.api.repository;

import com.example.api.model.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Integer> {

    Backlog findByProjectIdentifier(String projectId);
}
