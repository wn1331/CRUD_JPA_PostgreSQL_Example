package com.example.example.domain.user;

import com.example.example.api.response.UserResponseDTO;
import com.example.example.domain.BaseEntity;
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

    private String email;

    private String password;

    @Builder
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserResponseDTO toDto() {
        return new UserResponseDTO(this.id, this.email, this.password);
    }

    public User update(String email, String password) {
        this.email = email;
        this.password = password;
        return this;
    }
}
