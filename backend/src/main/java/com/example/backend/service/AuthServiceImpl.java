package com.example.backend.service;

import com.example.backend.dao.RefreshTokenDAO;
import com.example.backend.dao.UserDAO;
import com.example.backend.dto.AuthUserRequest;
import com.example.backend.dto.UserDTO;
import com.example.backend.entity.RefreshToken;
import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.security.JWTUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final RefreshTokenDAO refreshTokenDAO;

    private final PasswordEncoder passwordEncoder;

    private final JWTUtils jwtUtils;

    public AuthServiceImpl(UserService userService,
                           RefreshTokenDAO refreshTokenDAO,
                           PasswordEncoder passwordEncoder,
                           JWTUtils jwtUtils){
        this.userService = userService;
        this.refreshTokenDAO = refreshTokenDAO;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public Map<String, String> register(AuthUserRequest request) {

        if(userService.userExistsByUsername(request.getUsername())) {
            throw new RuntimeException("User is already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        userService.createUser(user);

        String jwt = jwtUtils.generateToken(user);
        String refresh = createRefreshToken(user);

        return Map.of("jwt", jwt, "refreshToken", refresh);
    }

    public Map<String, String> login(AuthUserRequest request) {
        User user = userService.getUserByUsername(request.getUsername());

        if(!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())){
            throw new RuntimeException("Invalid password");
        }

        String jwt = jwtUtils.generateToken(user);
        String refresh = createRefreshToken(user);

        return Map.of("jwt", jwt, "refresh", refresh);
    }

    public String createRefreshToken(User user) {
        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Instant.now().plus(7, ChronoUnit.DAYS));
        refreshTokenDAO.create(token);
        return token.getToken();
    }

    public Map<String, String> refreshToken(String refreshToken) {
        RefreshToken token = refreshTokenDAO.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found!"));

        if(token.getExpiryDate().isBefore((Instant.now()))){
            refreshTokenDAO.delete(token);
            throw new RuntimeException("Refresh token expired");
        }

        String jwt = jwtUtils.generateToken(token.getUser());
        return Map.of("jwt", jwt);
    }

    public void logout(User user) {
        refreshTokenDAO.deleteByUser(user);
    }
}
