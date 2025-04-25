package com.ust.ism.inventoryservice.repo;

import com.ust.ism.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

}
