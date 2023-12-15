package com.example.example.infrastructure.repository.book;

import com.example.example.domain.book.Book;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Book> findById(Long id);

}
