package com.project.bloggingappmongosb.service;

import com.project.bloggingappmongosb.collection.User;
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
public class UserServiceImpl implements UserService{
    private final MongoTemplate mongoTemplate;
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User createUser(User User) {
        LOGGER.info("Method createUser was called");
        return mongoTemplate.insert(User);
    }

    @Override
    public User getUserById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        User User = mongoTemplate.findById(id, User.class);

        if (User == null) {
            NotFoundException notFoundException = new NotFoundException("User" + id + "does not exist");
            LOGGER.error("User with id {} not found, class: {}", id, UserServiceImpl.class, notFoundException);
            throw notFoundException;
        }

        return User;
    }

    @Override
    public String deleteUser(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        User User = mongoTemplate.findAndRemove(query, User.class);
        if (User == null) {
            NotFoundException notFoundException = new NotFoundException("User " + id + " does not exist");
            LOGGER.error("User with id {} not found, class: {}", id, UserServiceImpl.class, notFoundException);
            throw notFoundException;
        }

        return "User deleted successfully";
    }


    @Override
    public List<User> getUsers() {
        LOGGER.info("Method getUsers was called");
        return mongoTemplate.findAll(User.class,"Users");
    }

    @Override
    public ResponseEntity<User> updateUser(String id,User updatedUser) {
        Query query = new Query(Criteria.where("_id").is(id));
        if (updatedUser == null) {
            NotFoundException notFoundException = new NotFoundException("User " + id + " does not exist");
            LOGGER.error("User with id {} not found, class: {}", id, UserServiceImpl.class, notFoundException);
            throw notFoundException;
        }
        Update update = new Update();
        update.set("name", updatedUser.getName());
        update.set("email", updatedUser.getEmail());
        update.set("password",updatedUser.getPassword());

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true); // Return the updated document

        User updatedDocument = mongoTemplate.findAndModify(query, update, options, User.class);

        if (updatedDocument == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedDocument);
    }
}
