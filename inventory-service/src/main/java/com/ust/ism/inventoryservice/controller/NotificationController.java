package com.ust.ism.inventoryservice.controller;

import com.ust.ism.inventoryservice.model.InventoryResponseDTO;
import com.ust.ism.inventoryservice.service.InventoryAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/notifications")
public class NotificationController {
    InventoryAlertService inventoryAlertService;

    @Autowired
    public NotificationController(InventoryAlertService inventoryAlertService){
        this.inventoryAlertService = inventoryAlertService;
    }

    @GetMapping("/low-stock")
    public List<InventoryResponseDTO> getLowStockNotification(){
       return inventoryAlertService.checkAndNotifyLowStockItems();
    }



}
