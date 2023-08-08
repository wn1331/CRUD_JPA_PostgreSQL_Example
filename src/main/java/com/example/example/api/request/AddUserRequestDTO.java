package com.example.example.api.request;

import com.example.example.domain.product.entity.Product;
import com.example.example.domain.user.entity.User;

public record AddUserRequestDTO(String email, String password) {

    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }

}
