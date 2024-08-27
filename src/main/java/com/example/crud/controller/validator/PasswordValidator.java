package com.example.crud.controller.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final String PW_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d{5,})(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,16}$";

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(PW_REGEXP);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
