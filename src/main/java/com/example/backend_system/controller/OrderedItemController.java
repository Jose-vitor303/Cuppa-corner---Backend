package com.example.backend_system.controller;


import com.example.backend_system.entities.OrderedItem;
import com.example.backend_system.services.OrderedItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordered_itens")
public class OrderedItemController {

    private final OrderedItemService orderedItemService;

    public OrderedItemController(OrderedItemService orderedItemService){
        this.orderedItemService = orderedItemService;
    }

    @GetMapping
    public ResponseEntity<List<OrderedItem>> getAllOrderedItens(){
        List<OrderedItem> orderedItemList = orderedItemService.getAllOrderedItens();

        if(orderedItemList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(orderedItemList);
        }
    }

    @PostMapping("/add/ordered_item")
    public void addOrderedItem(@RequestBody OrderedItem orderedItem){
        orderedItemService.addOrderedItem(orderedItem);
    }

}
