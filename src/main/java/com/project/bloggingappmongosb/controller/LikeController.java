package com.project.bloggingappmongosb.controller;


import com.project.bloggingappmongosb.collection.Like;
import com.project.bloggingappmongosb.service.LikeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public Like saveLike(@Valid @RequestBody Like Like){
        return likeService.createLike(Like);
    }

    @GetMapping("{id}")
    public Like getLikeById(@PathVariable String id){
        return likeService.getLikeById(id);
    }

    @DeleteMapping("{id}")
    public String deleteLike(@PathVariable String id){
        return likeService.deleteLike(id);
    }

    @GetMapping("/all")
    public List<Like> getLikes(){
        return likeService.getLikes();
    }

    @PutMapping("{id}")
    public ResponseEntity<Like> updateLike(@PathVariable String id,@Valid @RequestBody Like Like){
        return likeService.updateLike(id,Like);
    }
}
