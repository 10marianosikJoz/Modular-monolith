package com.marjoz.modulith.inventory;

import com.marjoz.modulith.inventory.dto.InventoryDto;

class InventoryMapper {

    InventoryDto toDto(InventoryEntity inventoryEntity) {
        return InventoryDto.builder()
                           .withId(inventoryEntity.id())
                           .withProductId(inventoryEntity.productId())
                           .withQuantity(inventoryEntity.quantity())
                           .build();
    }

    InventoryEntity toEntity(InventoryDto inventoryDto) {
        return InventoryEntity.builder()
                              .withId(inventoryDto.id())
                              .withProductId(inventoryDto.productId())
                              .withQuantity(inventoryDto.quantity())
                              .build();
    }
}