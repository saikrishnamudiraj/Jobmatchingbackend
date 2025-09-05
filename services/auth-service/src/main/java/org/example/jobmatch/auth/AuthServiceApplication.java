package org.example.jobmatch.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * AuthServiceApplication - main entry for the Auth Service module.
 *
 * Responsibilities:
 * - Boots Spring context for the auth module (security, JWT, OAuth2 client, JPA).
 * - Component-scan root is org.example.jobmatch.auth and subpackages.
 *
 * TODO:
 * - Add actuator, health checks and readiness probes.
 * - Add command-line runner to seed default roles (ROLE_USER, ROLE_RECRUITER, ROLE_ADMIN).
 */
@SpringBootApplication
@EntityScan("org.example.jobmatch.auth.entity")
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}