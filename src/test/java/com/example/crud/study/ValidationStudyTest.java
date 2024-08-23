package com.example.crud.study;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

class ValidationStudyTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
    }

    @ValueSource(strings = {"abcd", "abcdefgh"})
    @ParameterizedTest
    void validationNameSuccess(String name) {
        Data data = new Data(name);

        Set<ConstraintViolation<Data>> violations = validator.validate(data);
        assertThat(violations).isEmpty();
    }

    @NullAndEmptySource
    @ParameterizedTest
    void validationNameNotEmptyFail(String name) {
        Data data = new Data(name);

        Set<ConstraintViolation<Data>> violations = validator.validate(data);
        assertThat(violations).isNotEmpty();

        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .toList();
        assertThat(messages).contains("필수 입력값입니다.");

        List<String> paths = violations.stream()
                .map(ConstraintViolation::getPropertyPath)
                .map(Path::toString)
                .toList();
        assertThat(paths).contains("name");
    }

    @ValueSource(strings = {"abc", "abcdefghi"})
    @ParameterizedTest
    void validationNamePatternFail(String name) {
        Data data = new Data(name);

        Set<ConstraintViolation<Data>> violations = validator.validate(data);
        assertThat(violations).isNotEmpty();
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .toList();
        assertThat(messages).contains("소문자 4~8자리만 허용합니다.");

        List<String> paths = violations.stream()
                .map(ConstraintViolation::getPropertyPath)
                .map(Path::toString)
                .toList();
        assertThat(paths).contains("name");
    }

    @Getter
    static class Data {

        @NotBlank(message = "필수 입력값입니다.")
        @Pattern(regexp = "^[a-z]{4,8}$", message = "소문자 4~8자리만 허용합니다.")
        private String name;

        public Data(final String name) {
            this.name = name;
        }

    }

}
