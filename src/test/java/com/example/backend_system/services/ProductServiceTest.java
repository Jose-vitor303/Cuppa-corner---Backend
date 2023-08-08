package com.example.backend_system.services;

import com.example.backend_system.entities.Category;
import com.example.backend_system.entities.Product;
import com.example.backend_system.entities.User;
import com.example.backend_system.repository.ProductRepository;
import com.example.backend_system.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void setUp(){
        productService = new ProductService(productRepository);
    }


    @Test
    void canFindAll() {

        productService.findAll();
        verify(productRepository).findAll();
    }

    @Test
    void canSave() {

        BigDecimal bigDecimal = new BigDecimal(10);
        Category category = new Category("Food");
        Product product = new Product("Hamburger", "It's Delicious", bigDecimal, category, 0);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.save(product);

        verify(productRepository).save(product);

        assertNotNull(product);

        assertEquals(product, savedProduct);

    }
}