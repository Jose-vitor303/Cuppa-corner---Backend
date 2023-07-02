package com.example.backend_system.entities;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_ordered_item;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    private int amount;
}
