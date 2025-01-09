package com.marjoz.modulith.inventory;

import com.marjoz.modulith.product.ProductFacade;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
class InventoryModuleConfiguration {

    @Bean
    InventoryFacade inventoryFacade(InventoryRepository inventoryRepository,
                                    ProductFacade productFacade,
                                    ApplicationEventPublisher applicationEventPublisher) {

        return new InventoryFacade(inventoryRepository,
                                   productFacade,
                                   applicationEventPublisher,
                                   new InventoryMapper());
    }

    @Bean
    InventoryRepository inventoryRepository() {
        return new InventoryRepository();
    }
}