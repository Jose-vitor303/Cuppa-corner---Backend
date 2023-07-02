package com.example.backend_system.services;

import com.example.backend_system.entities.Category;
import com.example.backend_system.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void addCategory(@RequestBody Category category){
        Category category1 = new Category(category.getCategory_name());

        categoryRepository.save(category1);
    }


}
