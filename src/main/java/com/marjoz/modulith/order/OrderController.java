package com.marjoz.modulith.order;

import com.marjoz.modulith.order.dto.OrderDto;
import com.marjoz.modulith.order.dto.OrderItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
class OrderController {

    private final OrderFacade orderFacade;

    OrderController(final OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @GetMapping("/product-verification/{productId}")
    ResponseEntity<String> verifyProductExpirationDate(@PathVariable Long productId) {
        return ResponseEntity.ok(orderFacade.verifyProductExpirationDate(productId));
    }

    @GetMapping
    ResponseEntity<List<OrderDto>> findAllOrders() {
        return ResponseEntity.ok(orderFacade.findAll());
    }

    @GetMapping("/{orderId}")
    ResponseEntity<OrderDto> findOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderFacade.findOrderById(orderId));
    }

    @PostMapping
    ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderFacade.createOrder(orderDto));
    }

    @DeleteMapping("/{orderId}")
    ResponseEntity<Void> deleteOrderById(@PathVariable Long orderId) {
        orderFacade.deleteOrderById(orderId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{orderId}/items")
    ResponseEntity<OrderDto> addOrderItem(@PathVariable Long orderId, @RequestBody OrderItemDto orderItemDto) {
        return ResponseEntity.ok(orderFacade.addOrderItem(orderId, orderItemDto));
    }
}