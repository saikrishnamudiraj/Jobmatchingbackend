package org.example.jobmatch.auth.service;

import org.example.jobmatch.auth.dto.request.SignupRequest;
import org.example.jobmatch.auth.entity.User;
import org.example.jobmatch.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * AuthServiceImpl
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
@Service("authServiceimpl")
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public String signUp(SignupRequest signupRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(signupRequest.getEmail());
        if (optionalUser.isEmpty()) {
            User user = new User();
            user.setUsername(signupRequest.getUsername());
            user.setEmail(signupRequest.getEmail());
            user.setPassword(signupRequest.getPassword());
            userRepository.save(user);
        } else {
            throw new RuntimeException("User already exists");
        }
        return "User registered successfully";
    }

    @Override
    public void logIn(String username, String password) {

    }

    @Override
    public void logOut(String refreshToken) {

    }
    // TODO: add fields, constructors, and methods
}
