package org.example.jobmatchingbackend.userservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // disable CSRF for simplicity
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll() // allow all requests
//                );
//
//        return http.build();
//
//    }
}
