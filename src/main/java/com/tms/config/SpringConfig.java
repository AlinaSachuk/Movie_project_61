package com.tms.config;

import com.tms.domain.Movie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean("movieBean")
    public Movie getMovie() {
        return new Movie();
    }
}
