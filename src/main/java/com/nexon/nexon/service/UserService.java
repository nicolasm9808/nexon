package com.nexon.nexon.service;

import java.util.List;
import java.util.Optional;

import com.nexon.nexon.dto.UserRegistrationDTO;
import com.nexon.nexon.dto.UserUpdateDTO;
import com.nexon.nexon.entities.User;

public interface UserService {
    User registerUser(UserRegistrationDTO userRegistrationDTO);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    User updateUser(Long id, UserUpdateDTO userUpdateDTO);
    void deleteUser(Long id);
    List<User> getAllUsers();
}
