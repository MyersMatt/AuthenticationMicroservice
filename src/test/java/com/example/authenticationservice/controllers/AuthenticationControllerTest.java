package com.example.authenticationservice.controllers;

import com.example.authenticationservice.dtos.RegisterRequest;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
@SpringBootTest
public class AuthenticationControllerTest {
    @Autowired
    AuthenticationController authenticationController;

    @Before
    public void setup(){
        authenticationController.register(new RegisterRequest("testuser","password", "test@mail.com"));
    }
    @Test
    void registerNewUser_success(){
        RegisterRequest RR = new RegisterRequest("testuser2","password","test2@mail.com");
        ResponseEntity response = authenticationController.register(RR);
        assertTrue(HttpStatus.CREATED == response.getStatusCode());
        assertTrue(response.getBody().toString().contains(RR.getUsername()));
    }

    @Test
    void registerUserEmailTaken_expectedFailed(){
        RegisterRequest RR = new RegisterRequest("testuser","password", "test@mail.com");
        ResponseEntity response  = authenticationController.register(RR);
        assertEquals(HttpStatus.CONFLICT , response.getStatusCode());
        assertTrue(response.getBody().toString().contains("This email has already been registered to an account"));
    }
}
