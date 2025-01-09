package com.marjoz.modulith.inventory;

import com.marjoz.modulith.inventory.dto.InventoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventories")
class InventoryController {

    private final InventoryFacade inventoryFacade;

    InventoryController(final InventoryFacade inventoryFacade) {
        this.inventoryFacade = inventoryFacade;
    }

    @GetMapping
    ResponseEntity<List<InventoryDto>> getAllInventories() {
        var inventories = inventoryFacade.findAll();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{inventoryId}")
    ResponseEntity<InventoryDto> getInventoryById(@PathVariable Long inventoryId) {
        var inventory = inventoryFacade.findInventoryById(inventoryId);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/create")
    ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto) {
        var inventory = inventoryFacade.save(inventoryDto);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/{inventoryId}/add")
    ResponseEntity<InventoryDto> addToStock(@PathVariable Long inventoryId, @RequestParam int quantity) {
        var inventory = inventoryFacade.addToStock(inventoryId, quantity);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/{inventoryId}/remove")
    ResponseEntity<InventoryDto> removeFromStock(@PathVariable Long inventoryId, @RequestParam int quantity) {
        var inventory = inventoryFacade.removeFromStock(inventoryId, quantity);
        return ResponseEntity.ok(inventory);
    }
}