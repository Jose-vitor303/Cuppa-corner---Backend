package com.example.backend_system.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_purchase;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    private LocalDate request_date;
    private BigDecimal total_price;
}
