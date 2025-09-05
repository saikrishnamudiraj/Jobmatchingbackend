package org.example.jobmatch.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Role
 *
 * TODOs / responsibilities:
 * - Define core responsibilities of this class.
 * - Add validation and error handling.
 * - Write unit tests for happy and unhappy paths.
 *
 * Implementation notes:
 * - Implement methods to fulfill The responsibilities above.
 * - Add unit tests under src/test/java for this class.
 */
@Setter
@Getter
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseModel {
    // TODO: add fields, constructors, and methods
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    // optional description
    @Column(name = "description")
    private String description;
}
