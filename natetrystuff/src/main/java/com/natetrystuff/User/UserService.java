package com.natetrystuff.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        // Check if a user with the same Auth0 ID already exists
        if (userRepository.existsByAuth0Id(user.getAuth0Id())) {
            throw new IllegalStateException("User with the same Auth0 ID already exists.");
        }

        // Check if a user with the same username already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalStateException("Username is already taken.");
        }

        // Check if a user with the same email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalStateException("Email is already taken.");
        }

        return userRepository.save(user);
    }  

    public Optional<User> getUserByAuth0Id(String auth0Id) {
        return userRepository.findByAuth0Id(auth0Id);
    }

    public User createUser(String email, String auth0Id, String username) {
        User user = new User();
        user.setEmail(email);
        user.setAuth0Id(auth0Id);
        user.setUsername(username);
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            return null;
        }
    }

    public User edit(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
