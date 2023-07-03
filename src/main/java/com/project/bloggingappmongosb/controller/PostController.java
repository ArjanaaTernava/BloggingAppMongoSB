package com.project.bloggingappmongosb.controller;


import com.project.bloggingappmongosb.collection.Post;
import com.project.bloggingappmongosb.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post savePost(@Valid @RequestBody Post post){
        return postService.createPost(post);
    }

    @GetMapping("{id}")
    public Post getPostById(@PathVariable String id){
        return postService.getPostById(id);
    }

    @DeleteMapping("{id}")
    public String deletePost(@PathVariable String id){
        return postService.deletePost(id);
    }

    @GetMapping("/all")
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id,@Valid @RequestBody Post post){
      return postService.updatePost(id,post);
    }
}
