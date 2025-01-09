package com.marjoz.modulith.notification;

import com.marjoz.modulith.inventory.event.StockAddedEvent;
import com.marjoz.modulith.inventory.event.StockRemovedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.modulith.events.ApplicationModuleListener;

public class NotificationFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationFacade.class);

    @ApplicationModuleListener
    public void onStockAdded(StockAddedEvent event) {
        LOGGER.info("Added to stock: {}. Stock id: {}. Product id: {} .",event.quantity(),
                                                                         event.inventoryId(),
                                                                         event.productId());
    }

    @ApplicationModuleListener
    public void onStockRemoved(StockRemovedEvent event) {
        LOGGER.info("Removed from stock: {}. Stock id: {}. Product id: {} .",event.quantity(),
                                                                             event.inventoryId(),
                                                                             event.productId());
    }
}