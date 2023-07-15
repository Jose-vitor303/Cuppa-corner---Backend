package com.example.backend_system.controller;


import com.example.backend_system.entities.Product;
import com.example.backend_system.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productService.findAll();

        if(productList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(productList);
        }
    }

    @PostMapping("/add/product")
    public ResponseEntity<Object> addProduct(@RequestBody Product product){

        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
