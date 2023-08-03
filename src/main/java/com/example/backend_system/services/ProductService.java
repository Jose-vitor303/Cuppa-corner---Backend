package com.example.backend_system.services;

import com.example.backend_system.entities.Product;
import com.example.backend_system.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product save(Product product){
        productRepository.save(product);
        return product;
    }


}
