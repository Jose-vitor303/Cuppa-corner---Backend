package com.example.backend_system.services;

import com.example.backend_system.entities.OrderedItem;
import com.example.backend_system.repository.OrderedItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class OrderedItemService {

    private final OrderedItemRepository orderedItemRepository;

    public OrderedItemService(OrderedItemRepository orderedItemRepository){
        this.orderedItemRepository = orderedItemRepository;
    }

    public List<OrderedItem> getAllOrderedItens(){
        return orderedItemRepository.findAll();
    }

    public void addOrderedItem(@RequestBody OrderedItem orderedItem){

        OrderedItem orderedItem1 = new OrderedItem(orderedItem.getPurchase(), orderedItem.getProduct(), orderedItem.getAmount());
        orderedItemRepository.save(orderedItem1);
    }

}
