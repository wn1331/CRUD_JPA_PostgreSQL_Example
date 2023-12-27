package com.example.example.api.response;

import com.example.example.domain.Role;

public record UserResponseDTO(Long id, String email, String password, Role role) {


}
