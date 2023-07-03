package com.example.backend_system.services;


import com.example.backend_system.entities.Purchase;
import com.example.backend_system.repository.PurchaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> getAllPurchase(){
        return purchaseRepository.findAll();
    }

    public void addPurchase(@RequestBody Purchase purchase){

        Purchase purchase1 = new Purchase(purchase.getRequest_date(), purchase.getTotal_price());
        purchaseRepository.save(purchase1);
    }


}
