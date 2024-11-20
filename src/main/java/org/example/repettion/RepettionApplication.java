package org.example.repettion;

import org.example.repettion.bot.Bot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RepettionApplication {

    public static void main(String[] args) {

       SpringApplication.run(RepettionApplication.class, args);


    }

}
