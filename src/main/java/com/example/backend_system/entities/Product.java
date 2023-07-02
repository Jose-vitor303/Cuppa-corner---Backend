package com.example.backend_system.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_product;
    private String name;
    private String description;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category categories;

    public Product(String name, String description, BigDecimal price, Category categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = categories;
    }
}
