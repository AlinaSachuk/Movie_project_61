package com.tms.annotation;

import com.tms.util.FirstCharacterPlusLogic;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

@Documented
@Constraint(validatedBy = FirstCharacterPlusLogic.class)
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstCharacterPlus {
    String message() default "First character not +";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
