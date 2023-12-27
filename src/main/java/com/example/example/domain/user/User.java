package com.example.example.domain.user;

import com.example.example.api.response.UserResponseDTO;
import com.example.example.domain.BaseEntity;
import com.example.example.domain.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TABLE_USER")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String provider;
    private String providerId;

    @Builder
    public User(String name, String email, Role role, String provider, String providerId) {
        this.name = name;
        this.email = email;
        this.role=role;
        this.provider = provider;
        this.providerId = providerId;
    }

    public UserResponseDTO toDto() {
        return new UserResponseDTO(this.id,this.name, this.email, this.role);
    }

    public User update(String name, String email, Role role) {
        this.name=name;
        this.email = email;
        this.role=role;
        return this;
    }
}
