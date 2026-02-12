package com.example.todoapp.dto;

public class TodoDto {
    private Long id;
    private String task;
    private Boolean completed;

    public TodoDto() {
    }

    public TodoDto(Long id, String task, Boolean completed) {
        this.id = id;
        this.task = task;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}