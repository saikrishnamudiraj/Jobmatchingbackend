package org.example.jobmatch.auth.controller;

import org.example.jobmatch.auth.dto.request.SignupRequest;
import org.example.jobmatch.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController
 *
 * TODOs / responsibilities:
 * - Expose signup/login/refresh/logout endpoints.
 * - Validate requests and return AuthResponse.
 *
 * Implementation notes:
 * - Implement methods to fulfill responsibilities above.
 * - Add unit tests under src/test/java for this class.
 */

@RestController
@RequestMapping("/users")
public class AuthController {

    private AuthService authService;
    public AuthController(@Qualifier("authServiceimpl") AuthService authService) {
        this.authService = authService;
    }
    // TODO: add fields, constructors, and methods
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        authService.signUp(signupRequest);
        return ResponseEntity.ok("User registered successfully" + HttpStatus.OK);
    }
    public void login() {
    }
    public void refresh() {
    }
    public void logout() {
    }
    public void validateToken() {

    }
}
