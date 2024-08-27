package com.example.crud.controller.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final String PHONE_START_NUM = "010";

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (!value.startsWith(PHONE_START_NUM)) {
            return false;
        }
        Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
