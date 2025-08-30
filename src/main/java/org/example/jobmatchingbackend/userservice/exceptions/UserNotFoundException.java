package org.example.jobmatchingbackend.userservice.exceptions;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
