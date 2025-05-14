package com.ust.ism.inventoryservice.model;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public class InventoryRequestDTO {

    private  String itemName;
    private String itemDescription;
    private BigDecimal price;
    private  Long supplierId;
    private Double itemQunatity;
    private Long threshold;

    public Long getThreshold() {
        return threshold;
    }

    public void setThreshold(Long threshold) {
        this.threshold = threshold;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Double getItemQunatity() {
        return itemQunatity;
    }

    public void setItemQunatity(Double itemQunatity) {
        this.itemQunatity = itemQunatity;
    }
}
