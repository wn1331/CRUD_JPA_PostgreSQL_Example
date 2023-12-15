package com.example.example.infrastructure.repository.product;

import com.example.example.domain.product.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCustomRepository {
    List<Product> findAllProductsOrderByDescWhereAmountisDistinct(Pageable pageable, int amount);
    List<Product> findAllProductsJoinUser(Pageable pageable, String email);


}
