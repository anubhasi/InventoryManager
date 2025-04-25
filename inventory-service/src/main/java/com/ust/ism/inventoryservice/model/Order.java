package com.ust.ism.inventoryservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prod_id",referencedColumnName = "id")
    private Products products;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sup_id",referencedColumnName = "id")
    private Supplier supplier;

    @NotNull(message = "Quantity is required")
    private Long qty;
    @CreationTimestamp
    @Column(name = "ord_date",nullable = false,updatable = false)
    private LocalDateTime orderDate;
    private String orderdBy;
    @Column(name = "expct_del_date")
    private LocalDateTime expectedDeliveryDate;
    @Column(name = "act_del_date")
    private LocalDateTime actualDeliveryDate;
    @Column(name = "status")
    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public @NotNull(message = "Quantity is required") Long getQty() {
        return qty;
    }

    public void setQty(@NotNull(message = "Quantity is required") Long qty) {
        this.qty = qty;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderdBy() {
        return orderdBy;
    }

    public void setOrderdBy(String orderdBy) {
        this.orderdBy = orderdBy;
    }

    public LocalDateTime getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(LocalDateTime expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public LocalDateTime getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(LocalDateTime actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
