package org.example.jobmatchingbackend.userservice.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Role extends BaseModel{
    private String roleName;
    private String description;
    private String roleType;
    private String roleStatus;

}
