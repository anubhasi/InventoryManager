package com.ust.ism.inventoryservice.repo;

import com.ust.ism.inventoryservice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
//    @Query("SELECT o from OrderItem o where o. ")
    List<OrderItem> findByItemId(Long id);
}
