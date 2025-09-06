package org.example.jobmatch.auth.service;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.example.jobmatch.auth.dto.request.LoginRequest;
import org.example.jobmatch.auth.dto.request.SignupRequest;
import org.example.jobmatch.auth.dto.response.LoginResponse;
import org.example.jobmatch.auth.entity.Token;
import org.example.jobmatch.auth.entity.User;
import org.example.jobmatch.auth.repository.UserRepository;
import org.example.jobmatch.auth.security.JwtTokenProvider;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private PasswordEncoder bCryptPasswordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private TokenService tokenService;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder bCryptPasswordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider,
                           TokenService tokenService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenService = tokenService;
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
    @Transactional
    public LoginResponse logIn(LoginRequest loginRequest) {
        try {
            //authenticate the user from authentication manager.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword())
            );

            // At this point authentication succeeded.
            // Get the username (subject) to put in the JWT:
            String username = authentication.getName();

            // Generate access token (JWT)
            String accessToken = jwtTokenProvider.generateToken(username);

            // Load user entity (managed) to create refresh token (ensure user has id)
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalStateException("Authenticated user not found: " + username));

            // Create & persist refresh token (opaque string)
            Token refreshTokenEntity = tokenService.createRefreshToken(user);
            String refreshToken = refreshTokenEntity.getRefreshToken();

            long expiresInMs = jwtTokenProvider.getExpirationMs();

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setAccessToken(accessToken);
            loginResponse.setRefreshToken(refreshToken);
            loginResponse.setExpiresInMs(expiresInMs);
            return loginResponse;

        } catch (AuthenticationException e) {
            // don't return raw exception details to client
            throw new BadCredentialsException("Invalid username or password");
        }
//        Optional<User> optionalUser = userRepository.findByUsernameOrEmail(loginRequest.getUsername(), loginRequest.getEmail());
//        User user = null;
//        if (optionalUser.isPresent()) {
//            user = optionalUser.get();
//            if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//
//            }
//        } else {
//            throw new RuntimeException("User not found");
//        }
//
  //       return null;
    }

    @Override
    public void logOut(String refreshToken) {
        tokenService.revokeToken(refreshToken);
    }
    // TODO: add fields, constructors, and methods
}
