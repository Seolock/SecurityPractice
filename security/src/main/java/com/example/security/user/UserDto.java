package com.example.security.user;

import lombok.Data;

@Data
public class UserDto {

    private String username;
    private String password;

    public UserDto(){}

    public UserDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
