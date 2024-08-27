package com.example.crud.controller.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = PhoneValidator.class)
@Documented
public @interface Phone {

    String message() default "휴대폰 형식에 맞지 않습니다. ex) 010-1234-5678";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
