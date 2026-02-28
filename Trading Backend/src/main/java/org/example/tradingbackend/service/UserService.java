package org.example.tradingbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.tradingbackend.dto.ApiResponse;
import org.example.tradingbackend.dto.LoginRequest;
import org.example.tradingbackend.dto.RegisterRequest;
import org.example.tradingbackend.dto.UserResponse;
import org.springframework.stereotype.Service;
import org.example.tradingbackend.repository.UserRepository;
import org.example.tradingbackend.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ApiResponse register(RegisterRequest request) {
        try {
            // Validate input
            validateRegistrationInput(request);

            if (userRepository.findByUsername(request.getUsername()).isPresent()) {
                return new ApiResponse(false, "Username already exists", null);
            }

            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                return new ApiResponse(false, "Email already exists", null);
            }

            User user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .balance(new BigDecimal("100000.00"))
                    .build();

            userRepository.save(user);

            return new ApiResponse(true, "User registered successfully", null);
        } catch (IllegalArgumentException e) {
            return new ApiResponse(false, e.getMessage(), null);
        }
    }

    public ApiResponse login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());

        if (userOptional.isEmpty()) {
            return new ApiResponse(false, "User not found", null);
        }

        User user = userOptional.get();

        // Compare password (for production, use BCrypt)
        if (!user.getPassword().equals(request.getPassword())) {
            return new ApiResponse(false, "Invalid credentials", null);
        }

        UserResponse responseData = UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .balance(user.getBalance())
                .build();

        return new ApiResponse(true, "Login successful", responseData);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public ApiResponse getUserByUsername(String username) {

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return new ApiResponse(false, "User not found", null);
        }

        User user = userOptional.get();

        UserResponse responseData = UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .balance(user.getBalance())
                .build();

        return new ApiResponse(true, "User found", responseData);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private void validateRegistrationInput(RegisterRequest request) throws IllegalArgumentException {
        // Username validation
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }

        // Email validation - must end with @gmail.com
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!request.getEmail().toLowerCase().endsWith("@gmail.com")) {
            throw new IllegalArgumentException("Email must end with @gmail.com");
        }

        // Password validation - minimum 6 characters
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (request.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
    }
    public UserResponse getProfile(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .balance(user.getBalance())
                .build();
    }

}

