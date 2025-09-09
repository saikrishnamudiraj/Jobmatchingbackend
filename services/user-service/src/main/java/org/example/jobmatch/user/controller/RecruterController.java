package org.example.jobmatch.user.controller;


import org.example.jobmatch.user.dto.request.UserSearchRequest;
import org.example.jobmatch.user.dto.response.UserSummaryDto;
import org.example.jobmatch.user.service.UserQueryService;
import org.example.jobmatch.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecruterController {
    private final UserQueryService userQueryService;
    private final UserService userService;
    //private final UserService userService;



    public RecruterController(UserService userService, UserQueryService userQueryService) {
        this.userService = userService;
        this.userQueryService = userQueryService;
    }

    @PostMapping("/search")
    public Page<UserSummaryDto> searchUsers(@RequestBody UserSearchRequest request, Pageable pageable) {
        return userQueryService.search(request, pageable);
    }
}
