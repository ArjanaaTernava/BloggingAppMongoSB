package com.project.bloggingappmongosb.service;

import com.project.bloggingappmongosb.collection.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostService {
    Post createPost(Post post);

    Post getPostById(String id);

    String deletePost(String id);

    List<Post> getPosts();

    ResponseEntity<Post> updatePost(String id, Post post);
}
