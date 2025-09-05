package org.example.jobmatch.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig
 *
 * TODOs / responsibilities:
 * - Configure security filters (JWT & OAuth2).
 * - Permit /api/auth/** endpoints.
 *
 * Implementation notes:
 * - Implement methods to fulfill responsibilities above.
 * - Add unit tests under src/test/java for this class.
 */
@Configuration
public class SecurityConfig {
    // TODO: add fields, constructors, and methods
        @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF for simplicity
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // allow all requests
                );

        return http.build();

    }
}
