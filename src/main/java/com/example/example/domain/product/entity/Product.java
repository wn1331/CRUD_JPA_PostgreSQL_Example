package com.example.example.domain.product.entity;

import com.example.example.api.response.ProductResponseDTO;
import com.example.example.domain.BaseEntity;
import com.example.example.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TABLE_PRODUCT")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Product(String name, int amount, User user) {
        this.name = name;
        this.amount = amount;
        this.user = user;
    }

    public ProductResponseDTO toDto() {
        return new ProductResponseDTO(id, name, amount);
    }

    public Product update(String name, int amount) {
        this.name = name;
        this.amount = amount;
        return this;
    }

}
