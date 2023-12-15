package com.example.example.infrastructure.repository.reservation;

import com.example.example.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
