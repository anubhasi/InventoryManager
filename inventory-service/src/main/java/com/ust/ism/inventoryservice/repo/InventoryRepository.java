package com.ust.ism.inventoryservice.repo;

import com.ust.ism.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    @Query("SELECT i FROM Inventory i WHERE i.itemQunatity <= i.threshold")
    List<Inventory> findByItemQunatityLessThanOrEqualsThreshold();
}
