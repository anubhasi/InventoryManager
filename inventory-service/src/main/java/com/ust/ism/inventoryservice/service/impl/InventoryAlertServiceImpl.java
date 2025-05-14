package com.ust.ism.inventoryservice.service.impl;

import com.ust.ism.inventoryservice.model.Inventory;
import com.ust.ism.inventoryservice.model.InventoryResponseDTO;
import com.ust.ism.inventoryservice.model.OrderItem;
import com.ust.ism.inventoryservice.repo.InventoryRepository;
import com.ust.ism.inventoryservice.repo.OrderItemRepository;
import com.ust.ism.inventoryservice.repo.OrderRepository;
import com.ust.ism.inventoryservice.service.InventoryAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EnableScheduling
@Service
public class InventoryAlertServiceImpl implements InventoryAlertService {

    InventoryRepository inventoryRepository;
    OrderItemRepository orderItemRepository;


    @Autowired
    public InventoryAlertServiceImpl(InventoryRepository inventoryRepository,OrderItemRepository orderItemRepository){
        this.inventoryRepository = inventoryRepository;
        this.orderItemRepository =  orderItemRepository;
    }

    @Override
    public List<InventoryResponseDTO> checkAndNotifyLowStockItems() {
         List<InventoryResponseDTO> list = new ArrayList<>();
         List<Inventory> il = inventoryRepository.findByItemQunatityLessThanOrEqualsThreshold();
         il.forEach( a -> {
             InventoryResponseDTO responseDTO = new InventoryResponseDTO();
             responseDTO.setItemDescription(a.getItemDescription());
             responseDTO.setItemName(a.getItemName());
             responseDTO.setPrice(a.getPrice());
             responseDTO.setItemId(a.getId());
             list.add(responseDTO);
         });
         return list;
    }



    @Override
    public Integer suggestReorderQuantitysuggestReorderQuantity(Long itemId) {
        List<OrderItem> orders = orderItemRepository.findByItemId(itemId);

        if (orders.isEmpty()) {
            return 10; // default fallback
        }

        int totalOrdered = orders.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();

        int average = totalOrdered / orders.size();
        return average + 5; // add 5 units buffer
    }

    @Override
    public void reorderItem(Long itemId) {
            Inventory i = inventoryRepository.findById(itemId).orElseThrow(()-> new RuntimeException("Item not found"));
            i.setItemQunatity(i.getItemQunatity()+50);
            inventoryRepository.save(i);
    }

    @Scheduled(fixedRate = 86400000)//every 24 hrs
    public void autoReorder(){
        System.out.println("AutoReorder method started ");
        List<InventoryResponseDTO> inventoryResponseDTOS = checkAndNotifyLowStockItems();
        for(InventoryResponseDTO dto : inventoryResponseDTOS){
            Optional<Inventory> i = inventoryRepository.findById(dto.getItemId());
            i.ifPresent( k -> {
                k.setItemQunatity(k.getItemQunatity()+50);
                inventoryRepository.save(k);
            });
        }
        System.out.println("AutoReorder method ended ");

    }
}
