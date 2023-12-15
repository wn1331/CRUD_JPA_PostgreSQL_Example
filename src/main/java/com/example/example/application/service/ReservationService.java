package com.example.example.application.service;

import com.example.example.api.request.AddReservationRequestDto;
import com.example.example.domain.book.Book;
import com.example.example.domain.user.User;
import com.example.example.infrastructure.repository.book.BookRepository;
import com.example.example.infrastructure.repository.reservation.ReservationRepository;
import com.example.example.infrastructure.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;


    @Transactional
    public String insertReservation(AddReservationRequestDto dto) throws Exception {
        synchronized (this) {
            final User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new Exception("유저없음"));
            final Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new Exception("책없음"));

            Thread.sleep(10000);

            if (dto.amount() <= book.getRestAmount()) {
                book.minusAmount(dto.amount());
                reservationRepository.save(
                    AddReservationRequestDto.toEntity(dto.amount(), user, book));
            } else {
                throw new Exception("재고부족");
            }
        }
        return "도서 대여 성공";
    }



    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String insertReservation2(AddReservationRequestDto dto) throws Exception {
        final User user = userRepository.findById(dto.userId())
            .orElseThrow(() -> new Exception("유저없음"));
        final Book book = bookRepository.findById(dto.bookId())
            .orElseThrow(() -> new Exception("책없음"));

        Thread.sleep(10000);

        if (dto.amount() <= book.getRestAmount()) {
            book.minusAmount(dto.amount());
            reservationRepository.save(AddReservationRequestDto.toEntity(dto.amount(), user, book));
        } else {
            throw new Exception("재고부족");
        }
        return "도서 대여 성공";
    }

}
