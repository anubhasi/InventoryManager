package com.ust.ism.inventoryservice.service.impl;

import com.ust.ism.inventoryservice.model.Inventory;
import com.ust.ism.inventoryservice.repo.InventoryRepository;
import com.ust.ism.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }
    @Override
    public List<Inventory> getAllItem() {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventory> getItemById(Long id) {
        return inventoryRepository.findById(id);
    }

    @Override
    public Inventory saveItem(Inventory i) {
        return inventoryRepository.save(i);
    }

    @Override
    public Inventory updateItem(Inventory updatedItem, Long id) {
        return inventoryRepository.findById(id).map( item -> {
            item.setItemQunatity(updatedItem.getItemQunatity());
            item.setUpdatedBy(updatedItem.getUpdatedBy());
            return inventoryRepository.save(item);
        }).orElse(null);
    }

    @Override
    public void deleteItem(Long id) {
        inventoryRepository.deleteById(id);
    }
}
