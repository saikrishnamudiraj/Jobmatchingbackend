package org.example.jobmatch.auth.service;

import org.example.jobmatch.auth.dto.request.SignupRequest;

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
    public void logIn(String username, String password);
    public void logOut(String refreshToken);
}
