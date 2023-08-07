package com.example.example.domain.product.repository;

import com.example.example.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Object> findByName(String name);

}