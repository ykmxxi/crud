package com.example.crud.controller.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.validation.ConstraintValidatorContext;

class PhoneValidatorTest {

    private PhoneValidator validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        validator = new PhoneValidator();
        context = new ConstraintValidatorContextStub();
    }

    @Test
    void validateSuccess() {
        String phone = "010-1234-5678";

        assertThat(validator.isValid(phone, context)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"011-1234-5678", "01-1234-5678", "010-123-4567", "010-1234-567"})
    void validateFail(String phone) {
        assertThat(validator.isValid(phone, context)).isFalse();
    }

}
