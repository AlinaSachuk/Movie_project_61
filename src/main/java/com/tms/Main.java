package com.tms;

import com.tms.domain.Movie;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@ComponentScan("com.tms")
public class Main {
    private static final Logger log = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Movie ourMovie = (Movie) context.getBean("movie");

        System.out.println(ourMovie);

        ApplicationContext context1 = new AnnotationConfigApplicationContext(Main.class);
        SomeLogic logic = (SomeLogic) context1.getBean("someLogic");
        logic.firstTestMethod();
        logic.secondTestMethod();
    }
}
