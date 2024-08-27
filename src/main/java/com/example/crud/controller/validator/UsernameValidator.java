package com.example.crud.controller.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    private static final String USERNAME_REGEXP = "^[a-zA-Z]{3,12}$|^[ㄱ-ㅎ가-힣]{2,10}$";

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(USERNAME_REGEXP);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
