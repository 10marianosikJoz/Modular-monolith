package com.marjoz.modulith.order;

import com.marjoz.modulith.customer.dto.CustomerDto;
import com.marjoz.modulith.order.dto.OrderDto;
import com.marjoz.modulith.order.dto.OrderItemDto;
import com.marjoz.modulith.product.dto.ProductDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

class OrderDataProvider {

    OrderDto orderDto() {
        return OrderDto.builder()
                       .withId(1L)
                       .withCustomerId(customerDto().id())
                       .withOrderDate(LocalDate.of(2022, 1, 1))
                       .withTotalPrice(new BigDecimal("100.00"))
                       .withOrderItems(orderItemsDto())
                       .build();
    }

    CustomerDto customerDto() {
        return CustomerDto.builder()
                          .withId(1L)
                          .withName("John")
                          .withSurname("Doe")
                          .withEmail("customer@email.pl")
                          .withAddress("23 Main Street New York")
                          .withLoyaltyPoints(20L)
                          .build();
    }

    Set<OrderItemDto> orderItemsDto() {
        return Set.of(OrderItemDto.builder()
                                  .withId(1L)
                                  .withProductId(productDto().id())
                                  .withQuantity(1)
                                  .withPrice(new BigDecimal("20.00"))
                                  .build());
    }

    ProductDto productDto() {
        return ProductDto.builder()
                .withId(1L)
                .withName("Corn flakes")
                .withExpirationDate(LocalDate.of(2100, 1, 1))
                .build();
    }

    OrderItemDto orderItemDto() {
        return OrderItemDto.builder()
                           .withId(2L)
                           .withProductId(2L)
                           .withQuantity(2)
                           .withPrice(new BigDecimal("30.00"))
                           .build();
    }
}
