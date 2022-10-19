package com.example.authenticationservice.controllers;

import com.example.authenticationservice.dtos.RegisterRequest;
import com.example.authenticationservice.models.User;
import com.example.authenticationservice.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthenticationController {
    private static final Logger logger = Logger.getLogger(AuthenticationController.class.getName());
    private static final Level logLevel = Level.INFO;
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        logger.log(logLevel, () -> "Got to register api with request: " + registerRequest);
        User created = new User(0,
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getEmail()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(created));
    }

}
