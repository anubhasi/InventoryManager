package com.ust.ism.inventoryservice.repo;

import com.ust.ism.inventoryservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("Select o From  Order o JOIN FETCH o.products p JOIN FETCH o.supplier where o.id= :id ")
    Optional<Order> findOrderWithProductsAndSupplier(@Param("id") long id);
}
