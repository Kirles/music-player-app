package com.example.backend.service;


import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;


import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO createUser(UserDTO dto);

    void createUser(User user);

    UserDTO updateUser(UserDTO dto);

    void deleteUser(UserDTO dto);

    UserDTO getUserById(Long id);

    User getUserByUsername(String username);

    boolean userExistsByUsername(String username);

    List<UserDTO> getAllUsers();

}
