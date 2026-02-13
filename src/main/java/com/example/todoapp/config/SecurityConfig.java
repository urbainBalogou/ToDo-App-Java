package com.example.todoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * - Cette classe a UNE responsabilité : configurer la sécurité de l'application.
 * - Elle ne contient PAS de logique métier (authentification, validation...).
 *
 * ======================= PRINCIPE SOLID : D (Dependency Inversion) =======================
 *
 * SANS SOLID (avant) :
 *   - Les services auraient pu instancier directement new BCryptPasswordEncoder().
 *   - Couplage fort avec BCrypt → impossible de changer l'algorithme facilement.
 *
 * AVEC SOLID (maintenant) :
 *   - Le bean PasswordEncoder est défini ici et injecté via l'interface PasswordEncoder.
 *   - Les services dépendent de PasswordEncoder (abstraction), pas de BCryptPasswordEncoder.
 *   - Pour passer à Argon2, on change UNE ligne ici, sans toucher aux services.
 *
 * ======================= PRINCIPE SOLID : O (Open/Closed) ================================
 *
 * - La config est ouverte à l'extension (ajouter des filtres, des règles)
 *   et fermée à la modification (on n'a pas besoin de modifier les services
 *   pour changer la config de sécurité).
 * =========================================================================================
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()
                .anyRequest().permitAll()
            )
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form.disable())
            .logout(logout -> logout.disable());

        return http.build();
    }
}
