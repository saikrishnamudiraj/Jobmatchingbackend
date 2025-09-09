package org.example.jobmatch.user.service;

import org.example.jobmatch.user.dto.request.ProfileUpdateRequest;
import org.example.jobmatch.user.dto.request.UserSearchRequest;
import org.example.jobmatch.user.dto.response.ProfileUpdateResponse;
import org.example.jobmatch.user.entity.UserProfile;
import org.example.jobmatch.user.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
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
public class UserServiceImpl implements UserService {
    private final UserProfileRepository userProfileRepository;

    public UserServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public ProfileUpdateResponse updateProfie(ProfileUpdateRequest request) {

        ProfileUpdateResponse profileUpdateResponse = new ProfileUpdateResponse();
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(request.getFirstName());
        userProfile.setLastName(request.getLastName());
        userProfile.setSkills(request.getSkills());
        userProfile.setCertifications(request.getCertifications());
        userProfile.setEducations(request.getEducations());
        userProfile.setExperienceYears(request.getExperienceYears());
        userProfile.setSalary(request.getSalary());
        userProfile.setCompanies(request.getCompanies());
        userProfile.setProfileSummary(request.getProfileSummary());
        userProfileRepository.save(userProfile);
        profileUpdateResponse.setMessage("Profile updated successfully");
        return profileUpdateResponse;
    }

    @Override
    public void deleteProfile() {

    }

    @Override
    public void updateProfilePicture(String profilePictureUrl) {

    }

    @Override
    public void updateUserType(String userType) {

    }

    @Override
    public void updateUserStatus(String userStatus) {

    }
    // TODO: add fields, constructors, and methods
}
