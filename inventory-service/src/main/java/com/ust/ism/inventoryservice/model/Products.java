package com.ust.ism.inventoryservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Table(name = "products")
@Data
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "item_name")
    @NotBlank(message = "Item name is required")
    private  String itemName;
    @Column(name = "item_desc")
    private String itemDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }



    @Column(name = "item_category")
    private String itemCategory;

}
