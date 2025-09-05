package org.example.jobmatch.auth.repository;

import org.example.jobmatch.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = :username OR u.email = :email")
    Optional<User> findByUsernameOrEmail(@Param("username") String username,
                                         @Param("email") String email);
    Optional<User> findByUsername(String username);


    // If usernames are not unique and you still want "first" match:
    // Optional<User> findFirstByUsernameOrderByIdAsc(String username);
}