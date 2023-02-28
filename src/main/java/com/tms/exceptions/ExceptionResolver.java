package com.tms.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionResolver {
    private final Logger log = Logger.getLogger(this.getClass());

    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(ArithmeticException.class)
    public String myFirstExHand(Exception e){
        log.warn("Arithmetic exception: " + e);
        return "unsuccessfully";
    }
}
