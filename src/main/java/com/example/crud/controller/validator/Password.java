package com.example.crud.controller.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * 비밀번호 형식
 * - 대소문자 각각 1개 이상, 숫자 5개 이상, 특수문자를 포함한 8~16 자리 비밀번호
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface Password {

    String message() default "대소문자, 숫자 5개 이상, 특수문자를 포함해 8~16 자리의 비밀번호로 설정해주세요. ex) Ab!12345c";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
