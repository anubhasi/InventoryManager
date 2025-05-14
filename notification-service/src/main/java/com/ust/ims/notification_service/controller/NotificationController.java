package com.ust.ims.notification_service.controller;

import com.ust.ims.notification_service.dto.InventoryResponseDTO;
import com.ust.ims.notification_service.exception.ResourceNotFoundException;
import com.ust.ims.notification_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")

public class NotificationController {
    NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }


    @GetMapping("/low-stocks")
    public ResponseEntity<?> getLowStocks(){
        List<InventoryResponseDTO> list = notificationService.getLowStockItms();
        if(null != list ) {
            return ResponseEntity.ok(list);
        }
        else{
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Product service is currently down. Please try again later.");
        }
    }




}
