package com.example.backend_system.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_purchase;
    private LocalDate request_date;

    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "purchase_product_list",
            joinColumns = @JoinColumn(name = "id_purchase"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private List<Product> productList = new ArrayList<>();

    public Purchase( User user, List<Product> productList) {
        this.user = user;
        this.productList = productList;
        this.request_date = LocalDate.now();
        this.total = calculateTotal();

    }


    public BigDecimal calculateTotal(){
        BigDecimal total = BigDecimal.ZERO;

        for(Product product : productList){
            BigDecimal price = product.getPrice();
            int amount = product.getAmount();

            if(amount > 0){
                BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(amount));
                product.setAmount(amount);
                total = total.add(itemTotal);
            }else {
                total = total.add(price);
            }
        }

        return total;
    }

}
