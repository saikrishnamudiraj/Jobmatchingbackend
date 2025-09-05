package org.example.jobmatch.auth.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * LoginRequest
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
@Setter
@Getter
public class LoginRequest {
    // TODO: add fields, constructors, and methods
    private String username;
    private String email;
    private String password;
}
