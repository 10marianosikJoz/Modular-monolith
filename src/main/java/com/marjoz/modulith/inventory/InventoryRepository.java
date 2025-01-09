package com.marjoz.modulith.inventory;

import com.marjoz.modulith.inventory.exception.InventoryDomainException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class InventoryRepository {

    private static final Map<Long, InventoryEntity> DATABASE = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    InventoryEntity save(InventoryEntity inventoryEntity) {
        if (inventoryEntity.id() == null) {
            inventoryEntity = new InventoryEntity(ID_GENERATOR.getAndIncrement(),
                                                  inventoryEntity.productId(),
                                                  inventoryEntity.quantity());
        }
        DATABASE.put(inventoryEntity.id(), inventoryEntity);
        return inventoryEntity;
    }

    List<InventoryEntity> findAll() {
        return List.copyOf(DATABASE.values());
    }

    InventoryEntity findById(Long id) {
        var entity = DATABASE.get(id);
        if (entity == null) {
            throw new InventoryDomainException("Inventory not found");
        }
        return entity;
    }
}