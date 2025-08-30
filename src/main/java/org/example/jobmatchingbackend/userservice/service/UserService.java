package org.example.jobmatchingbackend.userservice.service;

import org.example.jobmatchingbackend.userservice.dtos.LoginRequestDto;
import org.example.jobmatchingbackend.userservice.dtos.LoginResponseDto;
import org.example.jobmatchingbackend.userservice.exceptions.UserNotFoundException;
import org.example.jobmatchingbackend.userservice.model.User;
import org.example.jobmatchingbackend.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser() {}
    public LoginResponseDto loginUser(LoginRequestDto loginRequestDto) throws UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(loginRequestDto.getEmail());
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found with email: "
                    + loginRequestDto.getEmail()
                    +"please signup first:");
        }
        loginResponseDto.setToken("token");

        return loginResponseDto;
    }
}
