package com.ust.ism.inventoryservice.service;


import com.ust.ism.inventoryservice.model.Order;

import java.util.List;

import java.util.Optional;

public interface OrderService {



    List<Order> getAllOrders();

    Optional<Order> getOrderById(Long id);

    Order saveOrder(Order order);

    Order updateOrderStatus(Long id,String status) throws Exception;
}
