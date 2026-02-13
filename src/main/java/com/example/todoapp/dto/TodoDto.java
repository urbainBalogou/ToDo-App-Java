package com.example.todoapp.dto;

/*
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * SANS SOLID (avant) :
 *   - On aurait passé l'entité Todo directement aux vues Thymeleaf.
 *   - La vue connaîtrait la structure de la BDD (ex: le champ User, createdAt...).
 *   - Si la BDD change → la vue casse.
 *
 * AVEC SOLID (maintenant) :
 *   - TodoDto ne transporte QUE les données nécessaires à la vue (id, task, completed).
 *   - Il ne contient PAS le champ User (donnée sensible/interne).
 *   - La conversion Entity <-> DTO est gérée par TodoMapper (SRP).
 * =========================================================================================
 */
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