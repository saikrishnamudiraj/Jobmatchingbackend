package org.example.jobmatch.auth.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * TokenUtil
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
@Component
public class TokenUtil {
    // TODO: add fields, constructors, and methods
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final Base64.Encoder URL_ENCODER = Base64.getUrlEncoder().withoutPadding();

    /**
     * Generate a secure URL-safe token with the given number of random bytes.
     * Typical: 32 bytes => 256 bits => ~43-character base64url string.
     */
    public String generateToken(int byteLength) {
        if (byteLength <= 0) throw new IllegalArgumentException("byteLength must be > 0");
        byte[] bytes = new byte[byteLength];
        SECURE_RANDOM.nextBytes(bytes);
        return URL_ENCODER.encodeToString(bytes);
    }

    /** Convenience for default length (32 bytes / 256 bits). */
    public String generateToken() {
        return generateToken(32);
    }
}
