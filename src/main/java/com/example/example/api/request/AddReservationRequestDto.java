package com.example.example.api.request;

import com.example.example.domain.book.Book;
import com.example.example.domain.reservation.Reservation;
import com.example.example.domain.user.User;
import lombok.Data;

public record AddReservationRequestDto(int amount, Long userId, Long bookId) {


    public static Reservation toEntity(int amount, User user, Book book){
        return Reservation.builder()
            .amount(amount)
            .user(user)
            .book(book)
            .build();
    }
}
