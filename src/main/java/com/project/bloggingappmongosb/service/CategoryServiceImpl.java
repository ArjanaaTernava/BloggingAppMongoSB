package com.project.bloggingappmongosb.service;

import com.project.bloggingappmongosb.collection.Category;
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
public class CategoryServiceImpl implements CategoryService{
    private final MongoTemplate mongoTemplate;
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    public CategoryServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Category createCategory(Category Category) {
        LOGGER.info("Method createCategory was called");
        return mongoTemplate.insert(Category);
    }

    @Override
    public Category getCategoryById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Category category = mongoTemplate.findById(id, Category.class);

        if (category == null) {
            NotFoundException notFoundException = new NotFoundException("Category" + id + "does not exist");
            LOGGER.error("Category with id {} not found, class: {}", id, CategoryServiceImpl.class, notFoundException);
            throw notFoundException;
        }

        return category;
    }

    @Override
    public String deleteCategory(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Category category = mongoTemplate.findAndRemove(query, Category.class);
        if (category == null) {
            NotFoundException notFoundException = new NotFoundException("Category " + id + " does not exist");
            LOGGER.error("Category with id {} not found, class: {}", id, CategoryServiceImpl.class, notFoundException);
            throw notFoundException;
        }

        return "Category deleted successfully";
    }


    @Override
    public List<Category> getCategories() {
        LOGGER.info("Method getCategories was called");
        return mongoTemplate.findAll(Category.class,"categories");
    }

    @Override
    public ResponseEntity<Category> updateCategory(String id,Category updatedCategory) {
        Query query = new Query(Criteria.where("_id").is(id));
        if (updatedCategory == null) {
            NotFoundException notFoundException = new NotFoundException("Category " + id + " does not exist");
            LOGGER.error("Category with id {} not found, class: {}", id, CategoryServiceImpl.class, notFoundException);
            throw notFoundException;
        }
        Update update = new Update();
        update.set("categoryName", updatedCategory.getCategoryName());
        update.set("description", updatedCategory.getDescription());

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true); // Return the updated document

        Category updatedDocument = mongoTemplate.findAndModify(query, update, options, Category.class);

        if (updatedDocument == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedDocument);
    }
}

