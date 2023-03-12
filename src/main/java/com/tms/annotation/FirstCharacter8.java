package com.tms.annotation;

import com.tms.util.FirstCharacter8Logic;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

@Documented
@Constraint(validatedBy = FirstCharacter8Logic.class)
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstCharacter8 {
    String message() default "First character not +";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
