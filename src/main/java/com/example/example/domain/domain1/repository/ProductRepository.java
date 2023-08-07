package com.example.example.domain.domain1.repository;

import com.example.example.domain.domain1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Object> findByName(String name);

}
