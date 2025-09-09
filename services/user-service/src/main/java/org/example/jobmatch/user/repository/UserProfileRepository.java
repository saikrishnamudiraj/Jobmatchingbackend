package org.example.jobmatch.user.repository;

import org.example.jobmatch.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>,
        JpaSpecificationExecutor<UserProfile> {
    public UserProfile save(UserProfile userProfile);
}