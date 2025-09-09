package org.example.jobmatch.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
public class ProfileUpdateRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<String> skills;
    private List<String> languages;
    private List<String> companies;
    private String profileSummary;
    private String profilePicture;
    private Integer experienceYears;
    private Double salary;
    private List<String> certifications;
    private List<String> educations;
    private List<String> interests;
}
