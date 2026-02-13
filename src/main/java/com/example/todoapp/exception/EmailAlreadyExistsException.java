package com.example.todoapp.exception;

/*
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * SANS SOLID (avant) :
 *   - On lançait des RuntimeException génériques partout :
 *     throw new RuntimeException("Cet email est déjà utilisé !")
 *   - Le contrôleur devait deviner le type d'erreur en lisant le message (fragile).
 *
 * AVEC SOLID (maintenant) :
 *   - Chaque type d'erreur métier a sa propre exception.
 *   - EmailAlreadyExistsException = doublon d'email à l'inscription.
 *   - ValidationException = données invalides.
 *   - Le contrôleur peut catcher des types spécifiques et réagir différemment.
 *
 * ======================= PRINCIPE SOLID : L (Liskov Substitution) ========================
 *
 * - Cette exception étend RuntimeException → elle peut être utilisée partout
 *   où RuntimeException est attendue, tout en portant un sens métier clair.
 * =========================================================================================
 */
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
