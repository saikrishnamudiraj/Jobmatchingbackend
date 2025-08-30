package org.example.jobmatchingbackend.userservice.advices;

import org.example.jobmatchingbackend.userservice.dtos.ErrorDto;
import org.example.jobmatchingbackend.userservice.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ErrorDto handleUserNotFoundException(UserNotFoundException e){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        return errorDto;
    }
}
