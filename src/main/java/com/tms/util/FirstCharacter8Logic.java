package com.tms.util;

import com.tms.annotation.FirstCharacter8;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FirstCharacter8Logic implements ConstraintValidator<FirstCharacter8, String > {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.startsWith("8");
    }
}
