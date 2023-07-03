package com.project.bloggingappmongosb.serviceImpl;

import com.project.bloggingappmongosb.collection.Comment;
import com.project.bloggingappmongosb.exception.NotFoundException;
import com.project.bloggingappmongosb.service.CommentService;
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
public class CommentServiceImpl implements CommentService {
    private final MongoTemplate mongoTemplate;
    private final static Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    public CommentServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Comment createComment(Comment Comment) {
        LOGGER.info("Method createComment was called");
        return mongoTemplate.insert(Comment);
    }

    @Override
    public Comment getCommentById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Comment Comment = mongoTemplate.findById(id, Comment.class);

        if (Comment == null) {
            NotFoundException notFoundException = new NotFoundException("Comment" + id + "does not exist");
            LOGGER.error("Comment with id {} not found, class: {}", id, CommentServiceImpl.class, notFoundException);
            throw notFoundException;
        }

        return Comment;
    }

    @Override
    public String deleteComment(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Comment Comment = mongoTemplate.findAndRemove(query, Comment.class);
        if (Comment == null) {
            NotFoundException notFoundException = new NotFoundException("Comment " + id + " does not exist");
            LOGGER.error("Comment with id {} not found, class: {}", id, CommentServiceImpl.class, notFoundException);
            throw notFoundException;
        }

        return "Comment deleted successfully";
    }


    @Override
    public List<Comment> getComments() {
        LOGGER.info("Method getComments was called");
        return mongoTemplate.findAll(Comment.class,"comments");
    }

    @Override
    public ResponseEntity<Comment> updateComment(String id,Comment updatedComment) {
        Query query = new Query(Criteria.where("_id").is(id));
        if (updatedComment == null) {
            NotFoundException notFoundException = new NotFoundException("Comment " + id + " does not exist");
            LOGGER.error("Comment with id {} not found, class: {}", id, CommentServiceImpl.class, notFoundException);
            throw notFoundException;
        }
        Update update = new Update();
        update.set("comment", updatedComment.getComment());
        update.set("commenter", updatedComment.getCommenter());

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true); // Return the updated document

        Comment updatedDocument = mongoTemplate.findAndModify(query, update, options, Comment.class);

        if (updatedDocument == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedDocument);
    }
}
