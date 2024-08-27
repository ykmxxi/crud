package com.example.crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.controller.request.SignUpRequest;
import com.example.crud.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody final SignUpRequest request) {
        log.info("signup request");
        if (existsDuplication(request)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.signUp(request);
        return new ResponseEntity<>(request.username() + "님 회원가입 성공", HttpStatus.CREATED);
    }

    private boolean existsDuplication(final SignUpRequest request) {
        return userService.checkEmailDuplication(request.email()) || userService.checkUsernameDuplication(
                request.username()) || userService.checkPhoneDuplication(request.phoneNumber());
    }

}
