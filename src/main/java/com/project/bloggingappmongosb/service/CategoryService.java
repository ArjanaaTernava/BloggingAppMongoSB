package com.project.bloggingappmongosb.service;

import com.project.bloggingappmongosb.collection.Category;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {
    Category createCategory(Category Category);

    Category getCategoryById(String id);

    String deleteCategory(String id);

    List<Category> getCategories();

    ResponseEntity<Category> updateCategory(String id, Category Category);

}
