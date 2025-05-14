package com.ust.ims.notification_service.dto;

import java.util.List;

public class ResponseList {
    private List<InventoryResponseDTO> list;

    public List<InventoryResponseDTO> getList() {
        return list;
    }

    public void setList(List<InventoryResponseDTO> list) {
        this.list = list;
    }
}
