package com.example.example.api.request;

import com.example.example.domain.user.User;

public record AddUserRequestDTO(String email, String name) {

    public User toEntity(){
        return User.builder()
                .email(email)
                .name(name)
                .build();
    }

}
