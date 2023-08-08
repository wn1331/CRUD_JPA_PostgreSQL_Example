package com.example.example.domain.product.repository;

import com.example.example.domain.product.entity.Product;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.example.example.domain.product.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> findAllProductsOrderByDescWhereAmountisDistinct(Pageable pageable, int amount) {
        return jpaQueryFactory.selectFrom(product)
                .where(product.amount.eq(amount))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(product.id.asc())
                .fetch();
    }

    @Override
    public List<Product> findAllProductsJoinUser(Pageable pageable, String email){
        return jpaQueryFactory.selectFrom(product)
                .join(product.user)
                .where(product.user.email.eq(email))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(product.id.asc())
                .fetch();
    }


}
