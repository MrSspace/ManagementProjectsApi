package com.management.projects.exception;

import com.management.projects.dto.response.SimpleMessage;
import com.mongodb.MongoWriteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.DateTimeException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MongoWriteException.class)
    public ResponseEntity<SimpleMessage> existentEmailExceptionManager(){
        return new ResponseEntity<>(
                new SimpleMessage("Existent Email. The introduced email currently has an account associated"),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<SimpleMessage> invalidEmailExceptionManager(InvalidEmailException ex){
        return new ResponseEntity<>(new SimpleMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<SimpleMessage> invalidPasswordExceptionManager(InvalidPasswordException ex){
        return new ResponseEntity<>(new SimpleMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<SimpleMessage> nullPointerExceptionManager(NullPointerException ex){
        return new ResponseEntity<>(new SimpleMessage("Value not found. Null Pointer. This resource must be created"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<SimpleMessage> parseDateExceptionManager(DateTimeException ex){
        return new ResponseEntity<>(new SimpleMessage("Invalid Date Format. Date Format must be yyyy-MM-dd"),
                HttpStatus.BAD_REQUEST);
    }

}
