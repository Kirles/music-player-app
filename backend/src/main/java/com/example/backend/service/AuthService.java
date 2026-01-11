package com.example.backend.service;

import com.example.backend.dto.AuthUserRequest;
import com.example.backend.entity.User;

import java.util.Map;

public interface AuthService {

    Map<String, String> register(AuthUserRequest request);

    Map<String, String> login(AuthUserRequest request);

    String createRefreshToken(User user);

    Map<String, String> refreshToken(String refreshToken);

    void logout(User user);

}
