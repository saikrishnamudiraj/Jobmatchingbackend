package org.example.jobmatch.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * User
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
@Entity
@Table(name = "users")
public class User extends BaseModel {
    // TODO: add fields, constructors, and methods
    @Column(name = "username", nullable = false)   // removed unique = true
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // Many users can have the same role (role is a separate entity)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    // One-to-many relationship to token (optional)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Token> tokens;

    // Simple collections of strings -> use element collections
    @ElementCollection
    @CollectionTable(name = "user_skills", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "skill")
    private List<String> skills;

    @ElementCollection
    @CollectionTable(name = "user_interests", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "interest")
    private List<String> interests;

    @ElementCollection
    @CollectionTable(name = "user_languages", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "language")
    private List<String> languages;

    @ElementCollection
    @CollectionTable(name = "user_certifications", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "certification")
    private List<String> certifications;

    @ElementCollection
    @CollectionTable(name = "user_educations", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "education")
    private List<String> educations;

    @ElementCollection
    @CollectionTable(name = "user_companies", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "company")
    private List<String> companies;

    @Column(name = "experience_years")
    private int experienceYears;

    @Column(name = "salary")
    private double salary;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "profile_summary", length = 2000)
    private String profileSummary;

    @Column(name = "is_premium")
    private boolean isPremium;
}
