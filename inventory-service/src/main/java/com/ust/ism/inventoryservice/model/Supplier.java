package com.ust.ism.inventoryservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "supplier")
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "supplier_name")
    @NotBlank
    private String supplierName;

    @OneToOne(mappedBy = "orders")
    private Inventory inventory;

    @OneToOne(mappedBy = "orders")
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public @NotBlank String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(@NotBlank String supplierName) {
        this.supplierName = supplierName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
