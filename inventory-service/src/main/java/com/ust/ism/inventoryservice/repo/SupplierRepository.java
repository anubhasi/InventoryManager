package com.ust.ism.inventoryservice.repo;

import com.ust.ism.inventoryservice.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
