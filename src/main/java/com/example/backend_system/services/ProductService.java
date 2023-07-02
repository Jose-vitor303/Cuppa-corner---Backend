package com.example.backend_system.services;

import com.example.backend_system.entities.Product;
import com.example.backend_system.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){// Get All Products
        return productRepository.findAll();
    }

    public void addProduct(@RequestBody Product data){
        Product product = new Product(data.getName(), data.getDescription(), data.getPrice(), data.getCategories());
        productRepository.save(product);
    }


}
