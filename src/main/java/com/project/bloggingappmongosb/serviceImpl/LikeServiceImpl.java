package com.project.bloggingappmongosb.serviceImpl;

import com.project.bloggingappmongosb.collection.Like;
import com.project.bloggingappmongosb.exception.NotFoundException;
import com.project.bloggingappmongosb.service.LikeService;
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
public class LikeServiceImpl implements LikeService {
    private final MongoTemplate mongoTemplate;
    private final static Logger LOGGER = LoggerFactory.getLogger(LikeServiceImpl.class);

    public LikeServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Like createLike(Like Like) {
        LOGGER.info("Method createLike was called");
        return mongoTemplate.insert(Like);
    }

    @Override
    public Like getLikeById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Like Like = mongoTemplate.findById(id, Like.class);

        if (Like == null) {
            NotFoundException notFoundException = new NotFoundException("Like" + id + "does not exist");
            LOGGER.error("Like with id {} not found, class: {}", id, LikeServiceImpl.class, notFoundException);
            throw notFoundException;
        }

        return Like;
    }

    @Override
    public String deleteLike(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Like Like = mongoTemplate.findAndRemove(query, Like.class);
        if (Like == null) {
            NotFoundException notFoundException = new NotFoundException("Like " + id + " does not exist");
            LOGGER.error("Like with id {} not found, class: {}", id, LikeServiceImpl.class, notFoundException);
            throw notFoundException;
        }

        return "Like deleted successfully";
    }


    @Override
    public List<Like> getLikes() {
        LOGGER.info("Method getLikes was called");
        return mongoTemplate.findAll(Like.class,"likes");
    }

    @Override
    public ResponseEntity<Like> updateLike(String id,Like updatedLike) {
        Query query = new Query(Criteria.where("_id").is(id));
        if (updatedLike == null) {
            NotFoundException notFoundException = new NotFoundException("Like " + id + " does not exist");
            LOGGER.error("Like with id {} not found, class: {}", id, LikeServiceImpl.class, notFoundException);
            throw notFoundException;
        }
        Update update = new Update();

        update.set("post", updatedLike.getPost());
        update.set("users", updatedLike.getUsers());

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true); // Return the updated document

        Like updatedDocument = mongoTemplate.findAndModify(query, update, options, Like.class);

        if (updatedDocument == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedDocument);
    }
}

