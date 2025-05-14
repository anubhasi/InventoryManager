package com.ust.ism.inventoryservice.controller;

import com.ust.ism.inventoryservice.service.InventoryAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reorder")
public class ReorderController {
    InventoryAlertService inventoryAlertService;

    @Autowired
    public ReorderController(InventoryAlertService inventoryAlertService){
        this.inventoryAlertService = inventoryAlertService;
    }


    @PostMapping("/{itemId}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<String> reorderItem(@PathVariable Long itemId) {
        inventoryAlertService.reorderItem(itemId);
        return ResponseEntity.ok("Reorder placed successfully");
    }
}
