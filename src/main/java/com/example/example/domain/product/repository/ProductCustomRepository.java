package com.example.example.domain.product.repository;

import com.example.example.domain.product.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCustomRepository {
    List<Product> findAllProductsOrderByDescWhereAmountisDistinct(Pageable pageable, int amount);
    List<Product> findAllInnerFetchJoinWithDistinct();
}
