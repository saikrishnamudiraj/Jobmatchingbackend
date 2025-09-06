package org.example.jobmatch.auth.controller;

import org.example.jobmatch.auth.dto.request.LoginRequest;
import org.example.jobmatch.auth.dto.request.SignupRequest;
import org.example.jobmatch.auth.dto.response.LoginResponse;
import org.example.jobmatch.auth.entity.Token;
import org.example.jobmatch.auth.service.AuthService;
import org.example.jobmatch.auth.service.TokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class AuthController {

    private AuthService authService;
    private TokenService tokenService;
    public AuthController( AuthService authService,
                          TokenService tokenService) {
        this.authService = authService;
        this.tokenService = tokenService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.logIn(loginRequest);
//        LoginResponse loginResponse = new LoginResponse();
//        loginResponse.setAccessToken(response.getAccessToken());
//        loginResponse.setRefreshToken(response.getRefreshToken());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        String token = authService.signUp(signupRequest);
        ResponseEntity<String> response = new ResponseEntity<>(token, HttpStatus.OK);

        return response;
    }

    public void refresh() {

    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody String refreshToken) {
        authService.logOut(refreshToken);
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }
    public void validateToken() {

    }
}
