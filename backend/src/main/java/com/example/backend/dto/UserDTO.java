package com.example.backend.dto;

import com.example.backend.entity.User;

public class UserDTO {

    private String username;

    private String email;

    private String role;

    private Boolean accountLocked;

    public UserDTO() {}

    public UserDTO(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.accountLocked = user.getAccountLocked();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getAccountLocked() {
        return this.accountLocked;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }


}
