package org.example.jobmatch.auth.repository;

import org.example.jobmatch.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository
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

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // TODO: add fields, constructors, and methods
    public User save(User user);
    public Optional<User> findByEmail(String email);
}
