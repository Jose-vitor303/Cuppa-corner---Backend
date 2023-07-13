package com.example.backend_system.services;


import com.example.backend_system.entities.Product;
import com.example.backend_system.entities.Purchase;
import com.example.backend_system.repository.ProductRepository;
import com.example.backend_system.repository.PurchaseRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }

    public Purchase save(Purchase purchase){
        return purchaseRepository.save(purchase);
    }

//    public void addProductToPurchase(Long purchaseId, Long productId) throws ChangeSetPersister.NotFoundException {
//
//        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(ChangeSetPersister.NotFoundException::new);
//        Product product = productRepository.findById(productId).orElseThrow(ChangeSetPersister.NotFoundException::new);
//
//        purchase.getProductList().add(product);
////        purchase.setTotal(calculateTotal(purchase.getProductList()));
//
//        purchaseRepository.save(purchase);
//    }

//    public void removeProductFromPurchase(Long purchaseId, Long productId) throws ChangeSetPersister.NotFoundException {
//
//        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(ChangeSetPersister.NotFoundException::new);
//        Product product = productRepository.findById(productId).orElseThrow(ChangeSetPersister.NotFoundException::new);
//
//        purchase.getProductList().remove(product);
//
////        purchase.setTotal(calculateTotal(purchase.getProductList()));
//        purchaseRepository.save(purchase);
//
//    }



}
