package org.example.jobmatchingbackend.userservice.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Token extends BaseModel{
    private String token;
   // private User user;
}
