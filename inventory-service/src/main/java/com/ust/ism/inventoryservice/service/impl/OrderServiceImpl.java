package com.ust.ism.inventoryservice.service.impl;

import com.ust.ism.inventoryservice.model.*;
import com.ust.ism.inventoryservice.repo.InventoryRepository;
import com.ust.ism.inventoryservice.repo.OrderRepository;
import com.ust.ism.inventoryservice.repo.SupplierRepository;
import com.ust.ism.inventoryservice.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryRepository inventoryRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,InventoryRepository inventoryRepository,SupplierRepository repository){
        this.orderRepository = orderRepository;
        this.inventoryRepository = inventoryRepository;
        this.supplierRepository = repository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    @Override
    public Order saveOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setStatus(OrderStatus.NEW.name());

        Supplier supplier = supplierRepository.findById(orderDTO.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        order.setSupplier(supplier);

        List<OrderItem> items = new ArrayList<>();
        long qunatity = 0;
        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            Inventory inventoryItem = inventoryRepository.findById(itemDTO.getInventoryItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(inventoryItem);
            orderItem.setQuantity(itemDTO.getQuantityOrdered());
            orderItem.setPriceAtOrder(inventoryItem.getPrice());
            orderItem.setOrder(order);
            qunatity  = qunatity + itemDTO.getQuantityOrdered();
            items.add(orderItem);
        }
        order.setOrderItems(items);
        order.setQty(qunatity);
        return orderRepository.save(order);
    }




    @Override
    public Order updateOrderStatus(Long id, String status) throws Exception {
      Order ord =  orderRepository.findById(id).orElseThrow(()-> new Exception("order with id is not present"));
      ord.setStatus(status);
     return orderRepository.save(ord);
    }

    @Override
    public Optional<List<Order>> getDelayedOrders() {
        return Optional.ofNullable(orderRepository.findAllOrdersByStatus());
    }
}
