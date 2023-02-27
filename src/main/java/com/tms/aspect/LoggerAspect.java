package com.tms.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Aspect
@Component
public class LoggerAspect {
    private final Logger log = Logger.getLogger(this.getClass());

    @Around("within(com.tms.*)")
    public void getLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        joinPoint.proceed();
        LocalTime end = LocalTime.now();
        log.info("Method worked time: " + (end.getNano() - start.getNano()));
    }

    @Before("within(com.tms.*)")
    public void getLogBefore(JoinPoint joinPoint) {
        log.info("Method " + joinPoint.getSignature() + " started!");
    }

    @After("within(com.tms.*)")
    public void getLogAfter(JoinPoint joinPoint) {
        log.info("Method " + joinPoint.getSignature() + " finished!");
    }

    @AfterReturning(value = "within(com.tms.*)", returning = "result")
    public void getLogAfterReturning(Object result) {
        log.info("Log after returning! - " + result);
    }

    @AfterThrowing(value = "within(com.tms.SomeLogic)", throwing = "err")
    public void getLogAfterThrowing(Throwable err) {
        log.warn("We HAVE THROW!!! " + err);
    }
}
