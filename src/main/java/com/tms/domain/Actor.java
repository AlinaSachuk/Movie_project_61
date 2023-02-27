package com.tms.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Actor {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String biography;
}
