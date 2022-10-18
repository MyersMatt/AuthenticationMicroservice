package com.example.authenticationservice.advice;

import com.example.authenticationservice.exceptions.EmailAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class RestExceptionHandler {
    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<Object> handleEmailAlreadyRegisteredException(HttpServletRequest request, EmailAlreadyRegisteredException emailAlreadyRegisteredException){
        String errorMessage = "This email has already been registered to an account";
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }
}
