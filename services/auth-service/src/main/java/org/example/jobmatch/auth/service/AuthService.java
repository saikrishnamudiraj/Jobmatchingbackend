package org.example.jobmatch.auth.service;

import org.example.jobmatch.auth.dto.request.LoginRequest;
import org.example.jobmatch.auth.dto.request.SignupRequest;
import org.example.jobmatch.auth.dto.response.LoginResponse;
import org.example.jobmatch.auth.entity.Token;
import org.example.jobmatch.auth.entity.User;

/**
 * AuthService
 *
 * TODOs / responsibilities:
 * - Define core responsibilities of this class.
 * - Add validation and error handling.
 * - Write unit tests for happy and unhappy paths.
 *
 * Implementation notes:
 * - Implement methods to fulfill responsibilities above.
 * - Add unit tests under src/test/java for this class.
 */
public interface AuthService {
    // TODO: add fields, constructors, and methods
    public String signUp(SignupRequest signupRequest);
    public LoginResponse logIn(LoginRequest loginRequest);
    public void logOut(String refreshToken);
}
