package com.ust.ism.inventoryservice.model;

import java.util.List;

public class OrderDTO {
    private Long supplierId;
    private List<OrderItemDTO> orderItems;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
