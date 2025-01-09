package com.marjoz.modulith.inventory;

import com.marjoz.modulith.inventory.dto.InventoryDto;
import com.marjoz.modulith.inventory.event.StockAddedEvent;
import com.marjoz.modulith.inventory.event.StockRemovedEvent;
import com.marjoz.modulith.product.ProductFacade;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryFacade {

    private final InventoryRepository inventoryRepository;
    private final ProductFacade productFacade;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final InventoryMapper inventoryMapper;

    InventoryFacade(final InventoryRepository inventoryRepository,
                    final ProductFacade productFacade,
                    final ApplicationEventPublisher applicationEventPublisher,
                    final InventoryMapper inventoryMapper) {

        this.inventoryRepository = inventoryRepository;
        this.productFacade = productFacade;
        this.applicationEventPublisher = applicationEventPublisher;
        this.inventoryMapper = inventoryMapper;
    }

    public List<InventoryDto> findAll() {
        return inventoryRepository.findAll().stream()
                                            .map(inventoryMapper::toDto)
                                            .collect(Collectors.toList());
    }

    public InventoryDto findInventoryById(Long id) {
        var inventory = inventoryRepository.findById(id);
        return inventoryMapper.toDto(inventory);
    }

    public InventoryDto save(InventoryDto inventoryDto) {
        var inventory = inventoryRepository.save(inventoryMapper.toEntity(inventoryDto));
        return inventoryMapper.toDto(inventory);
    }

    public InventoryDto addToStock(Long inventoryId, int quantity) {
        var inventory = inventoryRepository.findById(inventoryId);
        inventory = inventory.addToStock(quantity);
        inventoryRepository.save(inventory);

        var product = productFacade.findProductById(inventory.productId());

        applicationEventPublisher.publishEvent(new StockAddedEvent(inventoryId, quantity, product.id()));

        return inventoryMapper.toDto(inventory);
    }

    public InventoryDto removeFromStock(Long inventoryId, int quantity) {
        var inventory = inventoryRepository.findById(inventoryId);
        inventory = inventory.removeFromStock(quantity);
        inventoryRepository.save(inventory);

        var product = productFacade.findProductById(inventory.productId());

        applicationEventPublisher.publishEvent(new StockRemovedEvent(inventoryId, quantity, product.id()));

        return inventoryMapper.toDto(inventory);
    }
}