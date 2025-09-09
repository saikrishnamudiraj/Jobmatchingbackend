package org.example.jobmatch.user.service;

import org.example.jobmatch.user.dto.request.ProfileUpdateRequest;
import org.example.jobmatch.user.dto.request.UserSearchRequest;
import org.example.jobmatch.user.dto.response.ProfileUpdateResponse;

/**
 * UserService
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
public interface UserService {
    // TODO: add fields, constructors, and methods

    public ProfileUpdateResponse updateProfie(ProfileUpdateRequest profileUpdateRequest);
    public void deleteProfile();
    public void updateProfilePicture(String profilePictureUrl);
    public void updateUserType(String userType);
    public void updateUserStatus(String userStatus);
}
