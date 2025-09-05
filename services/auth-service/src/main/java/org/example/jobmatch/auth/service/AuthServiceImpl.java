package org.example.jobmatch.auth.service;

import org.example.jobmatch.auth.dto.request.LoginRequest;
import org.example.jobmatch.auth.dto.request.SignupRequest;
import org.example.jobmatch.auth.entity.Token;
import org.example.jobmatch.auth.entity.User;
import org.example.jobmatch.auth.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public String signUp(SignupRequest signupRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(signupRequest.getEmail());
        if (optionalUser.isEmpty()) {
            User user = new User();
            user.setUsername(signupRequest.getUsername());
            user.setEmail(signupRequest.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(signupRequest.getPassword()));
            userRepository.save(user);
        } else {
            throw new RuntimeException("User already exists");
        }
        return "User registered successfully";
    }

    @Override
    public User logIn(LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByUsernameOrEmail(loginRequest.getUsername(), loginRequest.getEmail());
        User user = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

            }
        } else {
            throw new RuntimeException("User not found");
        }

        return user;
    }

    @Override
    public void logOut(String refreshToken) {

    }
    // TODO: add fields, constructors, and methods
}
