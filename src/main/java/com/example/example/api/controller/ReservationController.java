package com.example.example.api.controller;

import com.example.example.api.request.AddReservationRequestDto;
import com.example.example.api.request.AddUserRequestDTO;
import com.example.example.api.response.UserResponseDTO;
import com.example.example.application.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reserv")
public class ReservationController {
    private final ReservationService reservationService;


    @Operation(summary = "도서 대여 요청", description = "도서가 대여 됩니다.", tags = {"ReservationController"})
    @PostMapping("/reservation")
    public ResponseEntity<String> joinUser(@RequestBody AddReservationRequestDto dto)
        throws Exception {
        return ResponseEntity.ok(reservationService.insertReservation(dto));
    }
}
