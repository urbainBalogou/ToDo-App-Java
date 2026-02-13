package com.example.todoapp.dto;

/*
 * ======================= PRINCIPE SOLID : I (Interface Segregation) =======================
 *
 * SANS SOLID (avant) :
 *   - On aurait réutilisé UserRegistrationDto pour le login.
 *   - Le formulaire de login verrait des champs inutiles (name, confirmPassword).
 *
 * AVEC SOLID (maintenant) :
 *   - UserLoginDto ne contient QUE email + password.
 *   - Le formulaire de login n'est pas pollué par des champs qu'il n'utilise pas.
 *   - C'est le principe ISP appliqué aux objets de transfert de données.
 * =========================================================================================
 */
public class UserLoginDto {
    private String email;
    private String password;

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
}