package com.example.authenticationservice.services;

import com.example.authenticationservice.exceptions.EmailAlreadyRegisteredException;
import com.example.authenticationservice.models.User;
import com.example.authenticationservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthenticationService {
    private static final Logger logger = Logger.getLogger(AuthenticationService.class.getName());
    private static final Level level = Level.INFO;
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        Optional<User> u = userRepository.findByEmail(user.getEmail());
        if (u.isPresent()) {
            logger.log(level, "User: " + u.get());
            throw new EmailAlreadyRegisteredException();
        }
        return userRepository.save(user);
    }
}
