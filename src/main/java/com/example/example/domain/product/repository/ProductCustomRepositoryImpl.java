package com.example.example.domain.product.repository;

import com.example.example.domain.product.entity.Product;
import com.example.example.domain.product.entity.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.example.example.domain.product.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> findAllProductsOrderByDescWhereAmountisDistinct() {
        return jpaQueryFactory.selectFrom(product)
                .where(product.amount.eq(1111))
                .orderBy(product.id.asc())
                .fetch();
    }

    @Override
    public List<Product> findAllInnerFetchJoinWithDistinct() {
        return null;
    }
}
