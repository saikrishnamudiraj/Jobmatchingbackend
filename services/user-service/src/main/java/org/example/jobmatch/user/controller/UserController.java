package org.example.jobmatch.user.controller;

import org.example.jobmatch.user.dto.request.ProfileUpdateRequest;
import org.example.jobmatch.user.dto.request.UserSearchRequest;
import org.example.jobmatch.user.dto.response.ProfileUpdateResponse;
import org.example.jobmatch.user.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * UserController
 *
 * TODOs / responsibilities:
 * - Expose user profile endpoints (get/update).
 * - Authorize access: users can update own profile; admin can manage users.
 *
 * Implementation notes:
 * - Implement methods to fulfill responsibilities above.
 * - Add unit tests under src/test/java for this class.
 */
@RestController
@RequestMapping("/seeker")
public class UserController {
    private  UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/user/profile")
    public ProfileUpdateResponse updateUserProfile(@RequestBody ProfileUpdateRequest request) {
        ProfileUpdateResponse response = userService.updateProfie(request);

        return response;
    }
}
