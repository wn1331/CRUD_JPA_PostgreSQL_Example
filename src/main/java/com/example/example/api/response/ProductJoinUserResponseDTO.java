package com.example.example.api.response;

import java.time.LocalDateTime;

public record ProductJoinUserResponseDTO(String name, int amount, String email, LocalDateTime createDate, LocalDateTime updateDate) {

}
