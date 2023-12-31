package com.example.backend_system.controller;

import com.example.backend_system.entities.Category;
import com.example.backend_system.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category Routes")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Operation(summary = "Fetch All Categories")
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categoryList = categoryService.findAll();

        if(categoryList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(categoryList);
        }
    }


    @Operation(summary = "Create a new Category")
    @PostMapping("/add/category")
    public ResponseEntity<Object> createCategory(@RequestBody Category category){

        Category category1 = new Category(category.getCategory_name());
        categoryService.save(category1);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
