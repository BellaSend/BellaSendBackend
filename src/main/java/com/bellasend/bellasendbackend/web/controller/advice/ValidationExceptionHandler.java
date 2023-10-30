package com.bellasend.bellasendbackend.web.controller.advice;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> validationExceptionHandler(ConstraintViolationException e){
        List<String> violations = e.getConstraintViolations().stream().map(
                constraintViolation -> constraintViolation.getPropertyPath().toString() + constraintViolation.getMessage().toString()
        ).toList();
        return new ResponseEntity<>(violations, HttpStatus.BAD_REQUEST);
    }
}
