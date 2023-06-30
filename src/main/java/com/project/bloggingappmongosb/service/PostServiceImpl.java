package com.project.bloggingappmongosb.service;

import com.project.bloggingappmongosb.collection.Post;
import com.project.bloggingappmongosb.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    private final MongoTemplate mongoTemplate;
    private final static Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);

    public PostServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Post createPost(Post Post) {
        LOGGER.info("Method createPost was called");
        return mongoTemplate.insert(Post);
    }

    @Override
    public Post getPostById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Post Post = mongoTemplate.findById(id, Post.class);

        if (Post == null) {
            NotFoundException notFoundException = new NotFoundException("Post" + id + "does not exist");
            LOGGER.error("Post with id {} not found, class: {}", id, PostServiceImpl.class, notFoundException);
            throw notFoundException;
        }

        return Post;
    }

    @Override
    public String deletePost(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Post Post = mongoTemplate.findAndRemove(query, Post.class);
        if (Post == null) {
            NotFoundException notFoundException = new NotFoundException("Post " + id + " does not exist");
            LOGGER.error("Post with id {} not found, class: {}", id, PostServiceImpl.class, notFoundException);
            throw notFoundException;
        }

        return "Post deleted successfully";
    }


    @Override
    public List<Post> getPosts() {
        LOGGER.info("Method getPosts was called");
        return mongoTemplate.findAll(Post.class,"Posts");
    }

    @Override
    public ResponseEntity<Post> updatePost(String id,Post updatedPost) {
        Query query = new Query(Criteria.where("_id").is(id));
        if (updatedPost == null) {
            NotFoundException notFoundException = new NotFoundException("Post " + id + " does not exist");
            LOGGER.error("Post with id {} not found, class: {}", id, PostServiceImpl.class, notFoundException);
            throw notFoundException;
        }
        Update update = new Update();
        update.set("title", updatedPost.getTitle());
        update.set("content", updatedPost.getContent());
        update.set("author",updatedPost.getAuthor());
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true); // Return the updated document

        Post updatedDocument = mongoTemplate.findAndModify(query, update, options, Post.class);

        if (updatedDocument == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedDocument);
    }
}
