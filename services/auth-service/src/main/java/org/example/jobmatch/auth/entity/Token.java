package org.example.jobmatch.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * RefreshToken
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "refresh_tokens", indexes = {
        @Index(name = "idx_refresh_token_token", columnList = "token")
})
public class Token extends BaseModel {

    @Column(nullable = false, unique = true, length = 512)
    private String token;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "issued_at")
    private LocalDateTime issuedAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    private boolean revoked;
}