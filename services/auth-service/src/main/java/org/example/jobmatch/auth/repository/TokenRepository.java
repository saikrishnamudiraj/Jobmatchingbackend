package org.example.jobmatch.auth.repository;

import org.example.jobmatch.auth.entity.Token;
import org.example.jobmatch.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * RefreshTokenRepository
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
public interface TokenRepository extends JpaRepository<Token, Long> {
    // TODO: add fields, constructors, and methods
    Token save(Token token);
    Optional<Token> findByToken(String token);
    List<Token> findAllByUserAndRevokedFalse(User user);
    void deleteByToken(String token);
}
