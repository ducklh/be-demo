package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    void deleteAllUsers();

    List<User> findByUsername(String username);
}
