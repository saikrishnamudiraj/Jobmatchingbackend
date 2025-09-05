package org.example.jobmatch.auth.service;

import jakarta.transaction.Transactional;
import lombok.Setter;
import org.example.jobmatch.auth.entity.Token;
import org.example.jobmatch.auth.entity.User;
import org.example.jobmatch.auth.repository.TokenRepository;
import org.example.jobmatch.auth.repository.UserRepository;
import org.example.jobmatch.auth.util.TokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * RefreshTokenServiceImpl
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
@Service("tokenServiceimpl")
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;
    private final TokenUtil tokenUtil;
    private final int tokenByteLength;
    private final long refreshTokenValiditySeconds;
    private final UserRepository userRepository;

    public TokenServiceImpl(TokenRepository tokenRepository,
                            TokenUtil tokenUtil,
                            @Value("${auth.refresh.token.byteLength:32}") int tokenByteLength,
                            @Value("${auth.refresh.token.validitySeconds:2592000}") long refreshTokenValiditySeconds
                            , UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.tokenUtil = tokenUtil;
        this.tokenByteLength = tokenByteLength;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Token createRefreshToken(User user) {
        // Try a few times to avoid rare collisions
        if (user == null) {
            throw new IllegalArgumentException("user must not be null");
        }
        Long userId = user.getId();
        if (userId == null) {
            throw new IllegalArgumentException("User must have an id before creating a refresh token");
        }

        // load managed user within transaction
        User managedUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // attempt to generate unique token
        for (int i = 0; i < 5; i++) {
            String candidate = tokenUtil.generateToken(this.tokenByteLength);
            if (tokenRepository.findByToken(candidate).isEmpty()) {
                Token token = new Token();
                token.setToken(candidate);
                token.setUser(managedUser);   // IMPORTANT: use managed entity
                token.setIssuedAt(LocalDateTime.now());
                token.setExpiresAt(LocalDateTime.now().plusSeconds(refreshTokenValiditySeconds));
                token.setRevoked(false);
                return tokenRepository.save(token);
            }
        }
        throw new IllegalStateException("Could not generate unique refresh token");
    }

    @Override
    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    @Transactional
    public boolean revokeToken(String tokenStr) {
        Optional<Token> t = tokenRepository.findByToken(tokenStr);
        if (t.isPresent()) {
            Token token = t.get();
            token.setRevoked(true);
            tokenRepository.save(token);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void revokeAllUserTokens(User user) {
        tokenRepository.findAllByUserAndRevokedFalse(user).forEach(t -> {
            t.setRevoked(true);
            tokenRepository.save(t);
        });
    }
}