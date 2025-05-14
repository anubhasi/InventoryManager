package com.ust.ism.inventoryservice.service.impl;

import com.ust.ism.inventoryservice.model.Inventory;
import com.ust.ism.inventoryservice.model.InventoryRequestDTO;
import com.ust.ism.inventoryservice.model.Supplier;
import com.ust.ism.inventoryservice.repo.InventoryRepository;
import com.ust.ism.inventoryservice.repo.SupplierRepository;
import com.ust.ism.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository,SupplierRepository supplierRepository){
        this.inventoryRepository = inventoryRepository;
        this.supplierRepository = supplierRepository;
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
    public Inventory saveItem(InventoryRequestDTO dto) {
        Inventory i = new Inventory();
        i.setItemQunatity(dto.getItemQunatity());
        i.setPrice(dto.getPrice());
        i.setItemName(dto.getItemName());
        i.setItemDescription(dto.getItemDescription());
        i.setThreshold(dto.getThreshold());
        Supplier supplier = supplierRepository.findById(dto.getSupplierId()).orElseThrow(() -> new RuntimeException("No such supplier exist"));
        i.setSupplier(supplier);
        return inventoryRepository.save(i);
    }

    @Override
    public Inventory updateItem(InventoryRequestDTO updatedItem, Long id) {

        return inventoryRepository.findById(id).map( item -> {
            item.setItemQunatity(updatedItem.getItemQunatity());
            item.setItemName(updatedItem.getItemName());
            item.setItemDescription(updatedItem.getItemDescription());
            item.setThreshold(updatedItem.getThreshold());
            item.setPrice(updatedItem.getPrice());
//            item.setUpdatedBy(updatedItem.getUpdatedBy());
            return inventoryRepository.save(item);
        }).orElse(null);
    }

    @Override
    public void deleteItem(Long id) {
        inventoryRepository.deleteById(id);
    }
}
