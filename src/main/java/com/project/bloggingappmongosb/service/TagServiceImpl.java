package com.project.bloggingappmongosb.service;

import com.project.bloggingappmongosb.collection.Tag;
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
public class TagServiceImpl implements TagService{
    private final MongoTemplate mongoTemplate;
    private final static Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);

    public TagServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Tag createTag(Tag Tag) {
        LOGGER.info("Method createTag was called");
        return mongoTemplate.insert(Tag);
    }

    @Override
    public Tag getTagById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Tag Tag = mongoTemplate.findById(id, Tag.class);

        if (Tag == null) {
            NotFoundException notFoundException = new NotFoundException("Tag" + id + "does not exist");
            LOGGER.error("Tag with id {} not found, class: {}", id, TagServiceImpl.class, notFoundException);
            throw notFoundException;
        }

        return Tag;
    }

    @Override
    public String deleteTag(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Tag Tag = mongoTemplate.findAndRemove(query, Tag.class);
        if (Tag == null) {
            NotFoundException notFoundException = new NotFoundException("Tag " + id + " does not exist");
            LOGGER.error("Tag with id {} not found, class: {}", id, TagServiceImpl.class, notFoundException);
            throw notFoundException;
        }

        return "Tag deleted successfully";
    }


    @Override
    public List<Tag> getTags() {
        LOGGER.info("Method getTags was called");
        return mongoTemplate.findAll(Tag.class,"Tags");
    }

    @Override
    public ResponseEntity<Tag> updateTag(String id,Tag updatedTag) {
        Query query = new Query(Criteria.where("_id").is(id));
        if (updatedTag == null) {
            NotFoundException notFoundException = new NotFoundException("Tag " + id + " does not exist");
            LOGGER.error("Tag with id {} not found, class: {}", id, TagServiceImpl.class, notFoundException);
            throw notFoundException;
        }
        Update update = new Update();
        update.set("tagName", updatedTag.getTagName());
        update.set("description", updatedTag.getDescription());
        update.set("posts",updatedTag.getPosts());

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true); // Return the updated document

        Tag updatedDocument = mongoTemplate.findAndModify(query, update, options, Tag.class);

        if (updatedDocument == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedDocument);
    }
}

