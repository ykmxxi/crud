package com.example.crud.controller.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.validation.ConstraintValidatorContext;

class PasswordValidatorTest {

    private PasswordValidator validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        validator = new PasswordValidator();
        context = new ConstraintValidatorContextStub();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ab@12345", "Ab!0123456789@cD"})
    void pwValidSuccess(String pw) {
        assertThat(validator.isValid(pw, context)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ab@1234", "AB@12345", "ab@12345", "Ab@!1234", "Ab123456", "Ab!0123456789@ccD"})
    void pwValidFail(String pw) {
        assertThat(validator.isValid(pw, context)).isFalse();
    }

}
