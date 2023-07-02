package com.example.backend_system.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_order;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    private LocalDate request_date;
    private BigDecimal total_price;
}
