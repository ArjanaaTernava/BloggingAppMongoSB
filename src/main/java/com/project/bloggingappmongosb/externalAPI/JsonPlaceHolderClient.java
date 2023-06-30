package com.project.bloggingappmongosb.externalAPI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "jsonplaceholder", url="https://restcountries.com/")
public interface JsonPlaceHolderClient {
    @GetMapping("/v3.1/all")
    List<Country> getBrewerys();

    @GetMapping("/breweries/{postId}")
    Country getBrewery(@PathVariable String postId);
}
