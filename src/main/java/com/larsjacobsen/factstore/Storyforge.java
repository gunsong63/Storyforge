package com.larsjacobsen.factstore;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Storyforge
{
    public static void main(String[] args) {
        //SpringApplication.run(FactstoreApplication.class, args);
        Application.launch(StoryforgeApplication.class,args);

    }
}
