package com.example.todoapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "todos")
public class Todo { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String task;

    @Column(name="is_completed")
    private boolean completed = false;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Todo() {}

    public Todo(String task) {
        this.task = task;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTask() { return task; }
    public void setTask(String task) { this.task = task; }
    
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}