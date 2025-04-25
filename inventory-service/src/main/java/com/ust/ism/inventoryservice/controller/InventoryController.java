package com.ust.ism.inventoryservice.controller;

import com.ust.ism.inventoryservice.model.Inventory;
import com.ust.ism.inventoryservice.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventory/item")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllItem(){
       return ResponseEntity.ok(inventoryService.getAllItem());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getItemById(@PathVariable Long id){
        Optional<Inventory> itemById = inventoryService.getItemById(id);
        return itemById.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Inventory> saveItem(@Valid @RequestBody Inventory inventory ){
        return new ResponseEntity<>(inventoryService.saveItem(inventory),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateItem(@Valid @RequestBody Inventory inventory,@PathVariable Long id){
        Inventory updatedItem = inventoryService.updateItem(inventory,id);
        return updatedItem != null ? ResponseEntity.ok(updatedItem) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id){
        inventoryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }


}
