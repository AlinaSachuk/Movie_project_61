package com.tms;

import org.springframework.stereotype.Component;

@Component
public class SomeLogic {

    void firstTestMethod(){
        System.out.println("Hello 1");
        System.out.println("Hello 2");
        System.out.println("Hello 3");
        throw new OutOfMemoryError();
    }

    String secondTestMethod(){
        System.out.println("Bye 1");
        System.out.println("Bye 2");
        System.out.println("Bye 3");
        return "123";
    }
}
