package com.management.projects.exception;

import com.management.projects.dto.response.SimpleMessageResponse;
import com.mongodb.MongoWriteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MongoWriteException.class)
    public ResponseEntity<SimpleMessageResponse> existentEmailExceptionManager(){
        return new ResponseEntity<>(
                new SimpleMessageResponse("Existent Email. The introduced email currently has an account associated"),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<SimpleMessageResponse> invalidEmailExceptionManager(InvalidEmailException ex){
        return new ResponseEntity<>(new SimpleMessageResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<SimpleMessageResponse> invalidPasswordExceptionManager(InvalidPasswordException ex){
        return new ResponseEntity<>(new SimpleMessageResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<SimpleMessageResponse> nullPointerExceptionManager(NullPointerException ex){
        return new ResponseEntity<>(new SimpleMessageResponse("Value not found. Null Pointer. This resource must be created"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<SimpleMessageResponse> parseDateExceptionManager(DateTimeException ex){
        return new ResponseEntity<>(new SimpleMessageResponse("Invalid Date Format. Date Format must be yyyy-MM-dd"),
                HttpStatus.BAD_REQUEST);
    }

}
