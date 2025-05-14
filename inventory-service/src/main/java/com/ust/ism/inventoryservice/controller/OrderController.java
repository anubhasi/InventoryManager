package com.ust.ism.inventoryservice.controller;

import com.ust.ism.inventoryservice.model.Order;
import com.ust.ism.inventoryservice.model.OrderDTO;
import com.ust.ism.inventoryservice.model.OrderStatusUpdateDTO;
import com.ust.ism.inventoryservice.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/getAll")
//    @PreAuthorize("hasAuthority('ROLE_INV_ADMIN')")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<List<Order>> getAllOrders(){
       List<Order> orderList = orderService.getAllOrders();
       return  ResponseEntity.ok(orderList);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Optional<Order> order = orderService.getOrderById(id);
        return  order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<Order> saveOrder(@Valid @NotNull @RequestBody OrderDTO order){
        Order o = orderService.saveOrder(order);
        return new ResponseEntity<>(o, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatusUpdateDTO orderStatus) throws Exception {
        Order o = orderService.updateOrderStatus(id,orderStatus.getStatus().name());
        return new ResponseEntity<>(o, HttpStatus.CREATED);
    }

    @GetMapping("/delayed")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<List<Order>> getDelayedOrders(){
        Optional<List<Order>> orders = orderService.getDelayedOrders();
        return  orders.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
