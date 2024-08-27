package com.example.crud.controller.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.validation.ConstraintValidatorContext;

class UsernameValidatorTest {

    private UsernameValidator validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        validator = new UsernameValidator();
        context = new ConstraintValidatorContextStub();
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "abcdefghijkl", "홍길동"})
    void validateSuccess(String username) {
        assertThat(validator.isValid(username, context)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab", "abcdefghijklm", "user1"})
    void validateFail(String username) {
        assertThat(validator.isValid(username, context)).isFalse();
    }

}
