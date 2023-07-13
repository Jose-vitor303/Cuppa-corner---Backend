package com.example.backend_system.controller;

import com.example.backend_system.entities.Category;
import com.example.backend_system.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categoryList = categoryService.findAll();

        if(categoryList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(categoryList);
        }
    }

    @PostMapping("/add/category")
    public ResponseEntity<Object> createCategory(@RequestBody Category category){

        Category category1 = new Category(category.getCategory_name());
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category1));
    }



}
