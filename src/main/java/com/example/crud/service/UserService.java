package com.example.crud.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.crud.controller.request.SignUpRequest;
import com.example.crud.domain.user.User;
import com.example.crud.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    public void signUp(final @Valid SignUpRequest request) {
        User user = User.of(request.username(), request.password(), request.email(), request.phoneNumber());
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean checkUsernameDuplication(final String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional(readOnly = true)
    public boolean checkEmailDuplication(final String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean checkPhoneDuplication(final String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

}
