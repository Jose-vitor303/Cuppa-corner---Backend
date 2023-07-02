package com.example.backend_system.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_category;
    private String category_name;

    public Category(String category_name) {
        this.category_name = category_name;
    }
}
