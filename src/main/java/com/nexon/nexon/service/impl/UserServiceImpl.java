package com.nexon.nexon.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nexon.nexon.dto.UserRegistrationDTO;
import com.nexon.nexon.dto.UserUpdateDTO;
import com.nexon.nexon.entities.User;
import com.nexon.nexon.repositories.UserRepository;
import com.nexon.nexon.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(UserRegistrationDTO userRegistrationDTO) {
        if (userRegistrationDTO.getDateOfBirth().isAfter(LocalDate.now().minusYears(14))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User must be at least 14 years old");
        }
        if (userRepository.existsByUsername(userRegistrationDTO.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        // Create a new user
        User user = new User();
        user.setFullName(userRegistrationDTO.getFullName());
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setDateOfBirth(userRegistrationDTO.getDateOfBirth());

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User existingUser = existingUserOpt.get();

        if (userUpdateDTO.getFullName() != null) {
            existingUser.setFullName(userUpdateDTO.getFullName());
        }
        if (userUpdateDTO.getBio() != null) {
            existingUser.setBio(userUpdateDTO.getBio());
        }
        if (userUpdateDTO.getProfilePicture() != null) {
            existingUser.setProfilePicture(userUpdateDTO.getProfilePicture());
        }
        if (userUpdateDTO.getPhoneNumber() != null) {
            existingUser.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        }

        return userRepository.save(existingUser);
    }

    

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
