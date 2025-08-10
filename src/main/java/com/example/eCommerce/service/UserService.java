package com.example.eCommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.eCommerce.entity.User;
import com.example.eCommerce.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// Register user
	public User registerUser(String username, String email, String password, String role) {
		if (userRepository.findByUsername(username).isPresent() || userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Username or email already exists");
        }

		return userRepository.save(new User(username, email, password, role));
	}

	// Login user
	public User loginUser(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
    }

	// List of all users
	public List<User> getAllUsers() {
        return userRepository.findAll();
    }

	// Get user by id
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }
}