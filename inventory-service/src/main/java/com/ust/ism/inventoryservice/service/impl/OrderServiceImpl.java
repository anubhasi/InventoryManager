package com.ust.ism.inventoryservice.service.impl;

import com.ust.ism.inventoryservice.model.Order;
import com.ust.ism.inventoryservice.repo.InventoryRepository;
import com.ust.ism.inventoryservice.repo.OrderRepository;
import com.ust.ism.inventoryservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,InventoryRepository inventoryRepository){
        this.orderRepository = orderRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findOrderWithProductsAndSupplier(id);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrderStatus(Long id, String status) throws Exception {
      Order ord =  orderRepository.findById(id).orElseThrow(()-> new Exception("order with id is not present"));
      ord.setStatus(status);
     return orderRepository.save(ord);
    }
}
