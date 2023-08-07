package com.example.example.api.request;

import com.example.example.domain.product.entity.Product;

public record AddProductRequestDTO(String name, Integer amount) {

    public Product toEntity(){
        return Product.builder()
                .name(name)
                .amount(amount)
                .build();
    }
}
