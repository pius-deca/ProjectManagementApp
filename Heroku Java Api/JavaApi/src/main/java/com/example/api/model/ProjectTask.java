package com.example.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "project_tasks")
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(updatable = false)
    private String projectSequence;

    @NotBlank(message = "Please include a project summary")
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority = 0;

    @Column(updatable = false)
    private String projectIdentifier;
    @Column(name = "due_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date dueDate;

    @Column(name = "created_at", updatable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MMM-dd")
    private Date createdAt;

    @Column(name = "updated_at")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MMM-dd")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "backlog_id", updatable = false, nullable = false)
    @JsonIgnore
    private Backlog backlog;

    public ProjectTask() {
    }

    @PrePersist
    protected void onCreatedAt(){
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdatedAt(){
        this.updatedAt = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectSequence() {
        return projectSequence;
    }

    public void setProjectSequence(String projectSequence) {
        this.projectSequence = projectSequence;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    @Override
    public String toString() {
        return "ProjectTask{" +
                "id = " + id +
                ", projectSequence = '" + projectSequence + '\'' +
                ", summary = '" + summary + '\'' +
                ", acceptanceCriteria = '" + acceptanceCriteria + '\'' +
                ", status = '" + status + '\'' +
                ", priority = '" + priority + '\'' +
                ", dueData = '" + dueDate + '\'' +
                ", projectIdentifier = '" + projectIdentifier + '\'' +
                ", created_at = '" + createdAt + '\'' +
                ", updated_at = '" + updatedAt +
                "}";
    }
}
