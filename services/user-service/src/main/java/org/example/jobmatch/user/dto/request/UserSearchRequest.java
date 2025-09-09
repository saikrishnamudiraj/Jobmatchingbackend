package org.example.jobmatch.user.dto.request;

import lombok.*;

import java.util.List;

/**
 * ProfileUpdateRequest
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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSearchRequest {
    // TODO: add fields, constructors, and methods
    private String q;
    private List<String> skills;
    private List<String> roles;
    private List<String> languages;
    private Integer minExperience;
    private Integer maxExperience;
    private Double minSalary;
    private Double maxSalary;
    private Integer page = 0;
    private Integer size = 20;
    private List<SortDto> sort;
    private List<String> interests;
    private List<String> certifications;
    private List<String> educations;
    private List<String> companies;
}
