package com.example.backend_system.services;

import com.example.backend_system.entities.Category;
import com.example.backend_system.repository.CategoryRepository;
import org.aspectj.lang.annotation.Before;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    @BeforeEach
    void setUp(){
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    void canFindAll() {

        categoryService.findAll();
        verify(categoryRepository).findAll();

    }

    @Test
    void canSave() {
        Category category = new Category(1L, "Food");

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category savedCategory = categoryService.save(category);

        verify(categoryRepository).save(category);

        assertNotNull(category);

        assertEquals(category, savedCategory);

    }
}