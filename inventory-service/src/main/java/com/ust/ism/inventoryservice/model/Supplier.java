package com.ust.ism.inventoryservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

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


    @OneToMany(mappedBy = "supplier",fetch = FetchType.LAZY,orphanRemoval = false)
    @JsonManagedReference
    private List<Inventory> items;

    @OneToMany(mappedBy = "supplier",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Order> orders;

    public List<Inventory> getItems() {
        return items;
    }

    public void setItems(List<Inventory> items) {
        this.items = items;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public @NotBlank String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(@NotBlank String supplierName) {
        this.supplierName = supplierName;
    }

}
