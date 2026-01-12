package com.example.backend.service;


import com.example.backend.entity.User;


import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getUserById(Long id);

    User getUserByUsername(String username);

    boolean userExistsByUsername(String username);

    List<User> getAllUsers();

}
