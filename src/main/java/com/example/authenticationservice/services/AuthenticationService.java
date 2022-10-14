package com.example.authenticationservice.services;

import com.example.authenticationservice.models.User;
import com.example.authenticationservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    public AuthenticationService(UserRepository userRepository){this.userRepository = userRepository;}
    public User register(User user) {
        return userRepository.save(user);
    }
}
