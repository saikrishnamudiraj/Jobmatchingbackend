package org.example.jobmatch.auth.dto.request;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class TokenRequest {
    private String refreshToken;
    private String accessToken;

}
