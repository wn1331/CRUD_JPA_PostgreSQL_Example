package com.example.example.domain.product.repository;

import com.example.example.domain.product.entity.Product;

import java.util.List;

public interface ProductCustomRepository {
    List<Product> findAllProductsOrderByDescWhereAmountisDistinct();
    List<Product> findAllInnerFetchJoinWithDistinct();
}
