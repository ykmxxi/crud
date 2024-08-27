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
@Constraint(validatedBy = UsernameValidator.class)
@Documented
public @interface Username {

    String message() default "작성자는 대소문자 1 ~ 12자리 및 한글 이름을 사용해야합니다. ex) User, 홍길동";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
