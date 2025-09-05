package org.example.jobmatch.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SignupRequest
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
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    // TODO: add fields, constructors, and methods
    private String username;
    private String email;
    private String password;
}
