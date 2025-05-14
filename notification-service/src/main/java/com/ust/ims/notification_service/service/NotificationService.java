package com.ust.ims.notification_service.service;

import com.ust.ims.notification_service.dto.InventoryResponseDTO;
import com.ust.ims.notification_service.exception.ResourceNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


public interface NotificationService {
     List<InventoryResponseDTO> getLowStockItms();
}





