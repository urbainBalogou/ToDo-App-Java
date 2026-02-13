package com.example.todoapp.exception;

/*
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * SANS SOLID (avant) :
 *   - On lançait des RuntimeException génériques pour les erreurs de validation.
 *   - Impossible de distinguer une erreur de validation d'une autre erreur métier.
 *
 * AVEC SOLID (maintenant) :
 *   - ValidationException est dédiée aux erreurs de validation de données.
 *   - On sait immédiatement que c'est un problème de saisie utilisateur.
 *   - Le contrôleur peut afficher un message d'erreur adapté selon le type.
 *
 * ======================= PRINCIPE SOLID : O (Open/Closed) ================================
 *
 * - Si on veut ajouter de nouvelles validations, on lance cette même exception.
 * - On peut aussi créer des sous-classes (ex: PasswordTooWeakException)
 *   sans modifier le code existant.
 * =========================================================================================
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
