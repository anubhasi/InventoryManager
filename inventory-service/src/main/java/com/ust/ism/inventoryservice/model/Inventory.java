package com.ust.ism.inventoryservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Data
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    @NotBlank(message = "Item name is required")
    private  String itemName;

    public @NotBlank(message = "Item name is required") String getItemName() {
        return itemName;
    }

    public void setItemName(@NotBlank(message = "Item name is required") String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "item_desc")
    private String itemDescription;
    @Column(name = "price")
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId
    @JoinColumn(name = "supplier_id")
    @JsonBackReference
    private Supplier supplier;

    @Column(name = "createdTs",nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDateTime createdTimeStamp;
    private String createdBy;
    @UpdateTimestamp
    @Column(name = "updatedTs")
    private LocalDateTime updatedTimeStamp;
    private String updatedBy;
    private Long threshold;
    @Column(name = "item_qty" )
    @NotNull(message = " quantity is required")
    @Min(value = 0, message = "quantity can't be negative")
    private Double itemQunatity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(LocalDateTime createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(LocalDateTime updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public Long getThreshold() {
        return threshold;
    }

    public void setThreshold(Long threshold) {
        this.threshold = threshold;
    }

    public @NotNull(message = " quantity is required") @Min(value = 0, message = "quantity can't be negative") Double getItemQunatity() {
        return itemQunatity;
    }

    public void setItemQunatity(@NotNull(message = " quantity is required") @Min(value = 0, message = "quantity can't be negative") Double itemQunatity) {
        this.itemQunatity = itemQunatity;
    }


    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
