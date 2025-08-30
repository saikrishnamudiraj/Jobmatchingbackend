package org.example.jobmatchingbackend.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseModel{
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String profileImageUrl;
    private String status;
    @ElementCollection
    @CollectionTable(name = "user_company", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "company")
    private List<String> company;

    private int experienceYears;

    @ElementCollection
    @CollectionTable(name = "user_skills", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "skill")
    private List<String> skills;

    @ElementCollection
    @CollectionTable(name = "user_jobs", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "job")
    private List<String> job;

    @ElementCollection
    @CollectionTable(name = "user_education", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "education")
    private List<String> education;
}
