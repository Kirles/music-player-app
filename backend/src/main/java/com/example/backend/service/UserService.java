package com.example.backend.service;


import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;


import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO createUser(UserDTO dto);

    void createUser(User user);

    UserDTO updateUser(UserDTO dto);

    void deleteUser(Long id);

    UserDTO getUserById(Long id);

    User getUserByUsername(String username);

    User getUserEntityById(Long id);

    boolean userExistsByUsername(String username);

    List<UserDTO> getAllUsers();

}
