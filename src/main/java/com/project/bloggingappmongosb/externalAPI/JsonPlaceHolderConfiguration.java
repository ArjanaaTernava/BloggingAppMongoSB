package com.project.bloggingappmongosb.externalAPI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonPlaceHolderConfiguration {
    @Bean("jsonplaceholder")
    CommandLineRunner runner(JsonPlaceHolderClient jsonPlaceHolderClient){
        return args -> {
            System.out.println("https://api.openbrewerydb.org/breweries is called");
            System.out.println(jsonPlaceHolderClient.getBrewerys().size());
        };
    }

}

