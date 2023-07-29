package com.example.backend_system.controller;
import com.example.backend_system.entities.Purchase;
import com.example.backend_system.services.PurchaseService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
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
        List<Purchase> purchaseList = purchaseService.findAll();

        if(purchaseList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(purchaseList);
        }
    }

    @PostMapping("/add/purchase/")
    public ResponseEntity<Object> createPurchase(@RequestBody Purchase purchase){

        Purchase purchase1 = new Purchase(purchase.getUser(), purchase.getProductList());
        purchaseService.save(purchase1);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("{purchaseId}/addProduct/{productId}/amount/{productAmount}")
    public ResponseEntity<Object> addProductToPurchase(@PathVariable Long purchaseId, @PathVariable Long productId, @PathVariable Long productAmount){

        try{
            purchaseService.addProductToPurchase(purchaseId, productId, productAmount);
            return ResponseEntity.ok("Produto adicionado com sucesso à compra");
        }catch (ChangeSetPersister.NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compra ou produto não encontrado");
        }
    }

    @DeleteMapping("{purchaseId}/removeProduct/{productId}/amount/{productAmount}")
    public ResponseEntity<Object> removeProductFromPurchase(@PathVariable Long purchaseId, @PathVariable Long productId, @PathVariable Long productAmount){

        try{
            purchaseService.removeProductFromPurchase(purchaseId, productId, productAmount);
            return ResponseEntity.ok("Produto removido com sucesso");
        }catch (ChangeSetPersister.NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compra ou produto não encontrado");
        }
    }
}
