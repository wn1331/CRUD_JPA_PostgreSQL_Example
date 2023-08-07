package com.example.example.api.request;

import com.example.example.domain.domain1.entity.Product;

public record AddProductRequestDTO(String name, Integer amount) {

    public Product toEntity(){
        return new Product(name, amount);
    }
}
