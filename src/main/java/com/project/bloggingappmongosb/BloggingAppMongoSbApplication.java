package com.project.bloggingappmongosb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
public class BloggingAppMongoSbApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloggingAppMongoSbApplication.class, args);
    }

}
