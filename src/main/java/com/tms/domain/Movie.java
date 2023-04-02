package com.tms.domain;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Table(name = "movie_table")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mov_id_seq_gen")
    @SequenceGenerator(name = "mov_id_seq_gen", sequenceName = "movie_table_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "year")
    private int year;

    @Column(name = "genre")
    private String genre;

    @Column(name = "rating")
    private double rating;

    @Column(name = "description")
    private String description;
}