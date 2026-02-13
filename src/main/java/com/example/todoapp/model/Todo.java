package com.example.todoapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

/*
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * SANS SOLID (avant) :
 *   - L'entité Todo n'avait pas de relation avec User.
 *   - Tous les utilisateurs voyaient toutes les tâches → pas d'isolation.
 *
 * AVEC SOLID (maintenant) :
 *   - L'entité Todo a UNE responsabilité : représenter une tâche en base de données.
 *   - La relation @ManyToOne avec User assure que chaque tâche appartient à UN utilisateur.
 *   - La logique métier (filtrage, sécurité) est dans le service, PAS dans le modèle.
 *
 * ======================= Architecture en couches ==========================================
 *
 * SANS SOLID (avant) :
 *   - On aurait pu passer l'entité Todo directement aux vues Thymeleaf.
 *   - La vue serait couplée à la structure de la base de données.
 *
 * AVEC SOLID (maintenant) :
 *   - L'entité Todo est convertie en TodoDto par TodoMapper avant d'aller à la vue.
 *   - Si la structure de la BDD change, la vue n'est pas impactée.
 * =========================================================================================
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}