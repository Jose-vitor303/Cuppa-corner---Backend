package com.example.backend_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;
    private String name;
    private String description;
    private BigDecimal price;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category categories;

    @JsonIgnore
    @ManyToMany(mappedBy = "productList")
    private List<Purchase> purchaseList = new ArrayList<>();

    public Product(String name, String description, BigDecimal price, Category categories, int amount){
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = categories;
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
