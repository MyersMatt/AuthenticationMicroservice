package com.example.authenticationservice.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {
    public EmailAlreadyRegisteredException() {
        super("This email is already associated with an account");
    }
}
