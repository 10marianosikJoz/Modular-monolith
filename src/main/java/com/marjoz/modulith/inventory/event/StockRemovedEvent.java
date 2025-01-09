package com.marjoz.modulith.inventory.event;

public record StockRemovedEvent(Long inventoryId,
                                Integer quantity,
                                Long productId) {}
