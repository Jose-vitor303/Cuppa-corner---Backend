package com.example.backend_system.services;


import com.example.backend_system.entities.Purchase;
import com.example.backend_system.repository.PurchaseRepository;
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

}
