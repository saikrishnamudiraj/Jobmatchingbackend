package org.example.jobmatchingbackend.userservice.controller;

import org.example.jobmatchingbackend.userservice.dtos.LoginRequestDto;
import org.example.jobmatchingbackend.userservice.dtos.LoginResponseDto;
import org.example.jobmatchingbackend.userservice.exceptions.UserNotFoundException;
import org.example.jobmatchingbackend.userservice.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) throws UserNotFoundException {
        LoginResponseDto loginResponseDto;
        loginResponseDto = userService.loginUser(loginRequestDto);
        return loginResponseDto;
    }

}
