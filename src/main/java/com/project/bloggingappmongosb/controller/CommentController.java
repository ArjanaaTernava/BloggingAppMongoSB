package com.project.bloggingappmongosb.controller;


import com.project.bloggingappmongosb.collection.Comment;
import com.project.bloggingappmongosb.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment saveComment(@Valid @RequestBody Comment Comment){
        return commentService.createComment(Comment);
    }

    @GetMapping("{id}")
    public Comment getCommentById(@PathVariable String id){
        return commentService.getCommentById(id);
    }

    @DeleteMapping("{id}")
    public String deleteComment(@PathVariable String id){
        return commentService.deleteComment(id);
    }

    @GetMapping("/all")
    public List<Comment> getComments(){
        return commentService.getComments();
    }

    @PutMapping("{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable String id, @Valid @RequestBody Comment Comment){
        return commentService.updateComment(id,Comment);
    }
}
