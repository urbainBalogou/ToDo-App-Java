package com.example.todoapp.dto;

/*
 * ======================= PRINCIPE SOLID : I (Interface Segregation) =======================
 *
 * SANS SOLID (avant) :
 *   - On aurait utilisé l'entité User pour le formulaire d'inscription.
 *   - Le formulaire verrait le champ "id" et d'autres champs internes.
 *   - Un seul objet User pour inscription ET connexion → champs inutiles exposés.
 *
 * AVEC SOLID (maintenant) :
 *   - UserRegistrationDto ne contient QUE les champs du formulaire d'inscription
 *     (name, email, password, confirmPassword).
 *   - UserLoginDto est un DTO SÉPARÉ pour la connexion (email, password).
 *   - Chaque DTO = exactement ce dont le formulaire a besoin, rien de plus (ISP).
 * =========================================================================================
 */
public class UserRegistrationDto {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}