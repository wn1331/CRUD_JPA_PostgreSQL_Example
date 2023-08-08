package com.example.example.api.request;

import com.example.example.domain.product.entity.Product;
import com.example.example.domain.user.entity.User;

public record AddProductRequestDTO(String name, Integer amount, User user) {

    public Product toEntity(){
        return Product.builder()
                .name(name)
                .amount(amount)
                .user(user)
                .build();
    }
}
