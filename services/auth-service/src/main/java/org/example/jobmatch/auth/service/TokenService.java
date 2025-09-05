package org.example.jobmatch.auth.service;

import org.example.jobmatch.auth.entity.Token;
import org.example.jobmatch.auth.entity.User;

import java.util.Optional;

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
    Token createRefreshToken(User user);
    Optional<Token> findByToken(String token);
    boolean revokeToken(String token);
    void revokeAllUserTokens(User user);
}
