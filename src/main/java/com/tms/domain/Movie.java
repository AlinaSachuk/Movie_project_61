package com.tms.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class Movie {
    private int id;
    private String movieName;
    private int year;
    private String genre;
    private double rating;
    private String description;
    @Autowired
    private Actor actor;
}
