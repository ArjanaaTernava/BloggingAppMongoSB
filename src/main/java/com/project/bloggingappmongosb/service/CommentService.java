package com.project.bloggingappmongosb.service;

import com.project.bloggingappmongosb.collection.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentService {
    Comment createComment(Comment Comment);

    Comment getCommentById(String id);

    String deleteComment(String id);

    List<Comment> getComments();

    ResponseEntity<Comment> updateComment(String id, Comment Comment);
}
