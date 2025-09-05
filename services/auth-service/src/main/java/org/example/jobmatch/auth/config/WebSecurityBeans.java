package org.example.jobmatch.auth.config;

import org.example.jobmatch.auth.entity.User;
import org.example.jobmatch.auth.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurityBeans
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
@Configuration
public class WebSecurityBeans {
    // TODO: add fields, constructors, and methods
    private final UserRepository userRepository;

    public WebSecurityBeans(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Expose a UserDetailsService that loads user from your UserRepository.
     * The returned UserDetails contains password and granted authorities built from role.name.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
            // convert your Role to GrantedAuthority
            var authorities = user.getRole() == null ? java.util.List.<org.springframework.security.core.authority.SimpleGrantedAuthority>of()
                    : java.util.List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority(user.getRole().getName()));

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities(authorities)
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        };
    }

    /**
     * Password encoder bean (BCrypt).
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager using DaoAuthenticationProvider wired with our UserDetailsService and PasswordEncoder.
     */
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }
}
