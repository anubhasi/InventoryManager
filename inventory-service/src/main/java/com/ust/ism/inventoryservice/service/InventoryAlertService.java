package com.ust.ism.inventoryservice.service;

import com.ust.ism.inventoryservice.model.Inventory;
import com.ust.ism.inventoryservice.model.InventoryResponseDTO;

import java.util.List;

public interface InventoryAlertService {

    public List<InventoryResponseDTO> checkAndNotifyLowStockItems();

    public Integer suggestReorderQuantitysuggestReorderQuantity(Long itemId);

    public void reorderItem(Long itemId);
}
