package com.example.example.api.request;

import com.example.example.domain.Role;

public record UpdateUserRequestDTO(Long id, String email, String userName, Role role) {
}
