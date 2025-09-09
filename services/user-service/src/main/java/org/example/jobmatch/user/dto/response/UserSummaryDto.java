package org.example.jobmatch.user.dto.response;

import lombok.*;

import java.util.List;

@Data
@Builder
public class UserSummaryDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer experienceYears;
    private Double salary;
    private String profileSummary;
    private List<String> skills;
    private List<String> languages;
    private List<String> companies;
    private String profilePicture;
    private String userType;
}
