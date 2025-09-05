package org.example.jobmatch.auth.controller;

import org.example.jobmatch.auth.dto.request.LoginRequest;
import org.example.jobmatch.auth.dto.request.SignupRequest;
import org.example.jobmatch.auth.dto.response.AuthResponse;
import org.example.jobmatch.auth.entity.Token;
import org.example.jobmatch.auth.entity.User;
import org.example.jobmatch.auth.service.AuthService;
import org.example.jobmatch.auth.service.TokenService;
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
    private TokenService tokenService;
    public AuthController(@Qualifier("authServiceimpl") AuthService authService,
                          @Qualifier("tokenServiceimpl") TokenService tokenService) {
        this.authService = authService;
        this.tokenService = tokenService;
    }
    // TODO: add fields, constructors, and methods


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = authService.logIn(loginRequest);
        AuthResponse authResponse;
        Token refreshToken = tokenService.createRefreshToken(user);
        ResponseEntity<AuthResponse> response;
        if (refreshToken != null) {
            authResponse = new AuthResponse();
            authResponse.setAccessToken(refreshToken.getToken());
            response = new ResponseEntity<>(authResponse, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        String token = authService.signUp(signupRequest);
        ResponseEntity<String> response = new ResponseEntity<>(token, HttpStatus.OK);

        return response;
    }

    public void refresh() {

    }
    public void logout() {
    }
    public void validateToken() {

    }
}
