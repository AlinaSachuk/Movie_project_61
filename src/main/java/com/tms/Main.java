package com.tms;

import com.tms.config.SpringConfig;
import com.tms.domain.Actor;
import com.tms.domain.Movie;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Movie myFirstObjectFromContainer = (Movie) context.getBean("movieBean");
        Actor mySecondObjectFromContainer = (Actor) context.getBean("actorBean");

        System.out.println(myFirstObjectFromContainer);
        System.out.println(mySecondObjectFromContainer);
    }
}
