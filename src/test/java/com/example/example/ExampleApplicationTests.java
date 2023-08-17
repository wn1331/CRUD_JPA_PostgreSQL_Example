package com.example.example;

import com.example.example.domain.product.Product;
import com.example.example.domain.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.example.domain.user.QUser.user;
import static com.example.example.domain.product.QProduct.product;
@SpringBootTest

class ExampleApplicationTests {

    @Autowired
    JPAQueryFactory queryFactory;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("QueryDsl 테스트중....onlySelect")
    public void querydsl_user(){
        String email = "wn1331@naver.com";
        List<User> result = queryFactory
                .select(user)
                .from(user)
                .where(user.email.eq(email))
                .fetch();
        result.forEach(user-> System.out.println(user.toDto().toString()));
    }
    @Test
    @DisplayName("QueryDsl 테스트중....ProductByJoin")
    public void querydsl(){
        String email = "wn1331@naver.com";
        List<Product> result = queryFactory
                .selectFrom(product)
                .join(product.user)
                .where(product.user.email.eq(email))
                .orderBy(product.id.asc())
                .fetch();
//        result.forEach(product-> System.out.println(product.toDto().toString()));
        result.forEach(p-> System.out.println(p.toJoinUserDto().toString()));
    }

}
