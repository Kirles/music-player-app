package com.example.backend.service;

import com.example.backend.dao.UserDAO;
import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDao;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserDAO userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    public UserDTO createUser(UserDTO dto) {
        return userMapper.toDto(userDao.create(userMapper.toEntity(dto)));
    }

    public void createUser(User user) {
        userDao.create(user);
    }

    public UserDTO updateUser(UserDTO dto) {
        return userMapper.toDto(userDao.update(userMapper.toEntity(dto)));
    }

    public void deleteUser(Long id) {
        userDao.delete(id);
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        return userMapper.toDto(userDao.findById(id).orElseThrow(() -> new RuntimeException("User not found!")));
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Transactional(readOnly = true)
    public User getUserEntityById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Override
    public boolean userExistsByUsername(String username) {
        return userDao.findByUsername(username).isPresent();
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userMapper.toDtoList(userDao.findAll());
    }

}
