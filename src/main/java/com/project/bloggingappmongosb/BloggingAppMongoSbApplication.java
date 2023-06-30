package com.project.bloggingappmongosb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableFeignClients
@SpringBootApplication
public class BloggingAppMongoSbApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloggingAppMongoSbApplication.class, args);
    }

}
