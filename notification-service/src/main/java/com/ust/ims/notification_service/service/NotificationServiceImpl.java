package com.ust.ims.notification_service.service;

import com.ust.ims.notification_service.dto.InventoryResponseDTO;
import com.ust.ims.notification_service.exception.ResourceNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl  implements NotificationService{

    //    InventoryAlertClients inventoryAlertClients;
    private final RestTemplate restTemplate;

    @Autowired
    public NotificationServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "myService" ,fallbackMethod = "fallbackNotification")
    public List<InventoryResponseDTO> getLowStockItms() {
//        if (Math.random() > 0.5) {
//            throw new RuntimeException("Service failed");
//        }
//        try {
            InventoryResponseDTO[] l = restTemplate.getForObject("http://localhost:9091/public/notifications/low-stock", InventoryResponseDTO[].class);
            return List.of(l);
//        }catch (Exception e){
//            System.out.println("exception while calling service");
//            throw new ResourceNotFoundException("service is down,try again later");
//        }
    }

    public List<InventoryResponseDTO> fallbackNotification(Throwable t) {
        System.out.println("inside fallBack method");
//        List<InventoryResponseDTO> list = new ArrayList<>();
        return null;
    }
}
