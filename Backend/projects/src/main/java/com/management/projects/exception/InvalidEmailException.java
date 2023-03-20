package com.management.projects.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidEmailException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String email;

    public InvalidEmailException(String email){
        super(String.format("Invalid email structure: %s", email));
        this.email = email;
    }

}
