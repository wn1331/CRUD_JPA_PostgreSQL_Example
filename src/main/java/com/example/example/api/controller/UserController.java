package com.example.example.api.controller;

import com.example.example.api.request.AddUserRequestDTO;
import com.example.example.api.request.UpdateUserRequestDTO;
import com.example.example.api.response.UserResponseDTO;
import com.example.example.application.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 가입 요청", description = "유저가 회원가입 됩니다.", tags = {"UserController"})
    @PostMapping("/join")
    public ResponseEntity<UserResponseDTO> joinUser(@RequestBody AddUserRequestDTO dto) {
        return ResponseEntity.ok(userService.joinUser(dto));
    }

    @Operation(summary = "유저 탈퇴 요청", description = "유저가 탈퇴처리 됩니다.", tags = {"UserController"})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @Operation(summary = "유저정보 수정 요청", description = "유저 정보가 수정됩니다.", tags = {"UserController"})
    @PatchMapping("/patch")
    public ResponseEntity<UserResponseDTO> updateuser(@RequestBody UpdateUserRequestDTO dto) {
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    @Operation(summary = "전체 유저 조회 요청", description = "유저 정보를 전부 조회합니다.", tags = {"UserController"})
    @GetMapping("/getall")
    public ResponseEntity<List<UserResponseDTO>> getUserList() {
        return ResponseEntity.ok(userService.getUserList());
    }

    @Operation(summary = "상품 조회 요청", description = "상품 정보를 조회합니다.", tags = {"UserController"})
    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
