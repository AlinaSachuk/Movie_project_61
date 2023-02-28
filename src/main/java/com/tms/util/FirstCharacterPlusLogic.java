package com.tms.util;

import com.tms.annotation.FirstCharacterPlus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FirstCharacterPlusLogic implements ConstraintValidator<FirstCharacterPlus, String > {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.startsWith("8");
    }
}
