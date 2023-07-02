package com.example.backend_system.repository;


import com.example.backend_system.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
