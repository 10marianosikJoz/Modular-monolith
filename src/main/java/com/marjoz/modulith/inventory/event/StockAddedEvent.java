package com.marjoz.modulith.inventory.event;

public record StockAddedEvent(Long inventoryId,
                              Integer quantity,
                              Long productId) {}
