package com.example.todoapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/*
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * - Cette classe a UNE responsabilité : représenter un utilisateur en base de données.
 * - Elle ne contient PAS de logique métier (validation, hachage de mot de passe).
 * - La validation est dans DefaultUserValidationService (SRP).
 * - Le hachage du mot de passe est dans UserRegistrationServiceImpl (SRP).
 *
 * ======================= Architecture en couches ==========================================
 *
 * SANS SOLID (avant) :
 *   - On aurait pu mettre la méthode isPasswordValid() directement dans User.
 *   - Le modèle aurait eu besoin de connaître BCrypt → couplage fort.
 *
 * AVEC SOLID (maintenant) :
 *   - User est un simple POJO/entité JPA, sans dépendance externe.
 *   - Les DTOs (UserRegistrationDto, UserLoginDto) transportent les données
 *     entre la vue et les services, protégeant l'entité des modifications directes.
 * =========================================================================================
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name="name")
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }

     public String getPassword() { return password; }

    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
