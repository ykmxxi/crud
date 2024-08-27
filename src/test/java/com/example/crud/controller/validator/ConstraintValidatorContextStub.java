package com.example.crud.controller.validator;

import jakarta.validation.ClockProvider;
import jakarta.validation.ConstraintValidatorContext;

public class ConstraintValidatorContextStub implements ConstraintValidatorContext {

    @Override
    public void disableDefaultConstraintViolation() {
    }

    @Override
    public String getDefaultConstraintMessageTemplate() {
        return "";
    }

    @Override
    public ClockProvider getClockProvider() {
        return null;
    }

    @Override
    public ConstraintViolationBuilder buildConstraintViolationWithTemplate(final String messageTemplate) {
        return null;
    }

    @Override
    public <T> T unwrap(final Class<T> type) {
        return null;
    }

}
