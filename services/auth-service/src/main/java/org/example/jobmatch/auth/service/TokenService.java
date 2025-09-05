package org.example.jobmatch.auth.service;

/**
 * RefreshTokenService
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
public interface TokenService {
    // TODO: add fields, constructors, and methods
    public String generateNewToken();
    public void validateToken(String token);
    public void deleteToken(String refreshToken);
}
