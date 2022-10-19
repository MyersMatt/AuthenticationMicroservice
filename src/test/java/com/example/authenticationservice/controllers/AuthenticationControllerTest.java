package com.example.authenticationservice.controllers;

import com.example.authenticationservice.dtos.RegisterRequest;
import com.example.authenticationservice.exceptions.EmailAlreadyRegisteredException;
import com.example.authenticationservice.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

@SpringBootTest
class AuthenticationControllerTest {
    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setup() {
        authenticationController.register(new RegisterRequest("testuser", "password", "test@mail.com"));
    }

    @AfterEach
    public void teardown() {
        userRepository.delete(userRepository.findByEmail("test@mail.com").get());
    }

    @Test
    void registerNewUser_success() {
        RegisterRequest RR = new RegisterRequest("testuser2", "password", "test2@mail.com");
        ResponseEntity response = authenticationController.register(RR);
        assertSame(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody().toString().contains(RR.getUsername()));
    }

    @Test
    void registerUserEmailTaken_ExceptionExpected() {
        RegisterRequest RR = new RegisterRequest("testuser", "password", "test@mail.com");
        Exception exception = assertThrows(EmailAlreadyRegisteredException.class, () -> authenticationController.register(RR));
        String expectedMessage = "This email is already associated with an account";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
}
