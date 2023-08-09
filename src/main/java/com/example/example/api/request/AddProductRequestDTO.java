package com.example.example.api.request;

import com.example.example.domain.product.Product;
import com.example.example.domain.user.User;

public record AddProductRequestDTO(String name, Integer amount, User user) {

    public Product toEntity(){
        return Product.builder()
                .name(name)
                .amount(amount)
                .user(user)
                .build();
    }
}
