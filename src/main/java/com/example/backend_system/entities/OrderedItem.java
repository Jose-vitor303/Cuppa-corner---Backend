package com.example.backend_system.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ordered_item;

    @ManyToOne
    @JoinColumn(name = "id_purchase")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    private int amount;
}
