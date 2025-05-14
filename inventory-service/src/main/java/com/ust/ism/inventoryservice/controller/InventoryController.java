package com.ust.ism.inventoryservice.controller;

import com.ust.ism.inventoryservice.exception.ValidationFailureException;
import com.ust.ism.inventoryservice.model.Inventory;
import com.ust.ism.inventoryservice.model.InventoryRequestDTO;
import com.ust.ism.inventoryservice.service.InventoryService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/inventory/item")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<List<Inventory>> getAllItem() {
        return ResponseEntity.ok(inventoryService.getAllItem());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<Inventory> getItemById(@PathVariable Long id) {
        Optional<Inventory> itemById = inventoryService.getItemById(id);
        return itemById.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<Inventory> saveItem(@RequestBody InventoryRequestDTO inventory) throws ValidationFailureException {
        Inventory updatedItem = null;
        try {
            updatedItem = inventoryService.saveItem(inventory);
        } catch (ConstraintViolationException e) {
            throw new ValidationFailureException("invalid request");
        }
        return new ResponseEntity<>(updatedItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<Inventory> updateItem(@Valid @RequestBody InventoryRequestDTO inventory, @PathVariable Long id) throws ValidationFailureException {
        Inventory updatedItem = null;
        try {
            updatedItem = inventoryService.updateItem(inventory, id);
        } catch (ConstraintViolationException e) {
            throw new ValidationFailureException("invalid request");
        }
        return updatedItem != null ? ResponseEntity.ok(updatedItem) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE')")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }


}
