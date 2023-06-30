package com.project.bloggingappmongosb.service;

import com.project.bloggingappmongosb.collection.Like;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LikeService {
    Like createLike(Like Like);

    Like getLikeById(String id);

    String deleteLike(String id);

    List<Like> getLikes();

    ResponseEntity<Like> updateLike(String id, Like Like);
}
