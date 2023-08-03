package com.example.backend_system.services;

import com.example.backend_system.entities.Category;
import com.example.backend_system.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category save(Category category){
        categoryRepository.save(category);
        return category;
    }


}
