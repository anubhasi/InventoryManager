package com.ust.ism.inventoryservice.service;

import com.ust.ism.inventoryservice.model.Inventory;
import com.ust.ism.inventoryservice.model.InventoryRequestDTO;

import java.util.List;

import java.util.Optional;

public interface InventoryService {

    List<Inventory> getAllItem();

    Optional <Inventory> getItemById(Long id);

    Inventory saveItem(InventoryRequestDTO i);

    Inventory updateItem(InventoryRequestDTO i , Long id);

    void deleteItem(Long id);


}
