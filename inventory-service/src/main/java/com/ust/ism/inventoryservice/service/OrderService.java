package com.ust.ism.inventoryservice.service;


import com.ust.ism.inventoryservice.model.Order;
import com.ust.ism.inventoryservice.model.OrderDTO;
import jakarta.transaction.Transactional;

import java.util.List;

import java.util.Optional;

public interface OrderService {



    List<Order> getAllOrders();

    Optional<Order> getOrderById(Long id);

    @Transactional
    Order saveOrder(OrderDTO orderDTO);

    Order updateOrderStatus(Long id, String status) throws Exception;

    Optional<List<Order>> getDelayedOrders();
}
