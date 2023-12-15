package com.example.example.infrastructure.repository.book;

import com.example.example.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

}
