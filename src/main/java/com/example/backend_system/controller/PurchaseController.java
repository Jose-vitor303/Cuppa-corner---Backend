package com.example.backend_system.controller;

import com.example.backend_system.entities.Purchase;
import com.example.backend_system.services.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService){
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases(){
        List<Purchase> purchaseList = purchaseService.getAllPurchase();

        if(purchaseList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(purchaseList);
        }
    }

    @PostMapping("/add/purchase")
    public void addPurchase(@RequestBody Purchase data){
        purchaseService.addPurchase(data);
    }


}
