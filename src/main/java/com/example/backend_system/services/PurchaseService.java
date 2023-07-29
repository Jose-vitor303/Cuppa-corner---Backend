package com.example.backend_system.services;


import com.example.backend_system.entities.Product;
import com.example.backend_system.entities.Purchase;
import com.example.backend_system.repository.ProductRepository;
import com.example.backend_system.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final ProductRepository productRepository;

    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }

    public void save(Purchase purchase){

        purchaseRepository.save(purchase);
    }


    public void removeProductFromPurchase(Long purchaseId, Long productId, Long productAmount) throws ChangeSetPersister.NotFoundException {

        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(ChangeSetPersister.NotFoundException::new);

        BigDecimal numberAmount = new BigDecimal(productAmount);
        BigDecimal priceProduct = (product.getPrice()).multiply(numberAmount);
        BigDecimal priceTotal = purchase.getTotal();
        product.setAmount(Math.toIntExact(productAmount + 1));

        purchase.getProductList().remove(product);

        purchase.setTotal(priceTotal.subtract(priceProduct));

        purchaseRepository.save(purchase);

        }


    public void addProductToPurchase(Long purchaseId, Long productId, Long productAmount) throws ChangeSetPersister.NotFoundException {

        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(ChangeSetPersister.NotFoundException::new);

        BigDecimal numberAmount = new BigDecimal(productAmount);
        purchase.getProductList().add(product);
        product.setAmount(Math.toIntExact(productAmount));

//        purchase.getProductList().contains(product);

        BigDecimal priceProduct = (product.getPrice()).multiply(numberAmount);
        BigDecimal priceTotal = purchase.getTotal();

        purchase.setTotal(priceTotal.add(priceProduct));

        purchaseRepository.save(purchase);


    }
}
