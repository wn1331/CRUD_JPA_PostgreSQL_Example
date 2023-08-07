package com.example.example.domain.domain1.entity;

import com.example.example.api.response.ProductResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int amount;

    public Product(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public Product(Long id, String name, int amount){
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public ProductResponseDTO toDto() {
        return new ProductResponseDTO(id, name, amount);
    }
}
