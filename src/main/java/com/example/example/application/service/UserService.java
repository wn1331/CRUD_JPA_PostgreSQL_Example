package com.example.example.application.service;

import com.example.example.api.request.AddUserRequestDTO;
import com.example.example.api.request.UpdateUserRequestDTO;
import com.example.example.api.response.UserResponseDTO;
import com.example.example.domain.user.User;
import com.example.example.infrastructure.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Transactional
    public UserResponseDTO joinUser(AddUserRequestDTO req){
        userRepository.findByEmail(req.email())
                .ifPresent(existingUser -> {
                    throw new IllegalArgumentException("해당하는 이메일의 회원이 이미 존재합니다.!");
                });
        User user = req.toEntity();
        return userRepository.save(user).toDto();
    }

    @Transactional
    public UserResponseDTO deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("해당하는 회원을 찾을 수 없습니다!"));
        UserResponseDTO res = user.toDto();
        userRepository.delete(user);
        return res;
    }


    @Transactional
    public UserResponseDTO updateUser(UpdateUserRequestDTO req) {
        User user = userRepository.findById(req.id())
                .orElseThrow(() -> new NotFoundException("해당하는 회원을 찾을 수 없습니다!"));
        return userRepository.save(user.update(req.email(),req.password())).toDto();
    }

    public List<UserResponseDTO> getUserList(){
        List<UserResponseDTO> userList = new ArrayList<>();

        userRepository.findAll().forEach(user -> userList.add(user.toDto()));

        return userList;
    }

    public UserResponseDTO getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 회원이 없습니다.."));
        return user.toDto();
    }


    public Page<UserResponseDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(User::toDto);
    }


}
