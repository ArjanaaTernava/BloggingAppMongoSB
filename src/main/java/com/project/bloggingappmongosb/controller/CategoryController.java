package com.project.bloggingappmongosb.controller;


import com.project.bloggingappmongosb.collection.Category;
import com.project.bloggingappmongosb.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category saveCategory(@RequestBody Category Category){
        return categoryService.createCategory(Category);
    }

    @GetMapping("{id}")
    public Category getCategoryById(@PathVariable String id){
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("{id}")
    public String deleteCategory(@PathVariable String id){
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/all")
    public List<Category> getCategorys(){
        return categoryService.getCategories();
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable String id, @RequestBody Category Category){
        return categoryService.updateCategory(id,Category);
    }
}
