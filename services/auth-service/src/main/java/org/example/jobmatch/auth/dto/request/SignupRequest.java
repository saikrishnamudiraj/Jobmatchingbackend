package org.example.jobmatch.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    private String username;
    private String email;
    private String password;
}
