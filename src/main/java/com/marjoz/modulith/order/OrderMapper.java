package com.marjoz.modulith.order;

import com.marjoz.modulith.order.dto.OrderDto;
import com.marjoz.modulith.order.dto.OrderItemDto;

import java.util.Set;
import java.util.stream.Collectors;

class OrderMapper {

    OrderDto toDto(OrderEntity orderEntity) {
        return OrderDto.builder()
                       .withId(orderEntity.id())
                       .withCustomerId(orderEntity.customerId())
                       .withOrderDate(orderEntity.orderDate())
                       .withTotalPrice(orderEntity.totalPrice())
                       .withOrderItems(toOrderItemDtoSet(orderEntity.orderItems()))
                       .build();
    }

    Set<OrderItemDto> toOrderItemDtoSet(Set<OrderItemEntity> orderItemEntities) {
        return orderItemEntities.stream()
                                .map(this::toOrderItemDto)
                                .collect(Collectors.toSet());
    }

    OrderItemDto toOrderItemDto(OrderItemEntity orderItemEntity) {
        return OrderItemDto.builder()
                           .withId(orderItemEntity.id())
                           .withProductId(orderItemEntity.productId())
                           .withQuantity(orderItemEntity.quantity())
                           .withPrice(orderItemEntity.price())
                           .build();
    }

    OrderEntity toEntity(OrderDto orderDto) {
        return OrderEntity.builder()
                          .withId(orderDto.id())
                          .withCustomerId(orderDto.customerId())
                          .withOrderDate(orderDto.orderDate())
                          .withTotalPrice(orderDto.totalPrice())
                          .withOrderItems(toOrderItemEntitySet(orderDto.orderItems()))
                          .build();
    }

    Set<OrderItemEntity> toOrderItemEntitySet(Set<OrderItemDto> orderItemDto) {
        return orderItemDto.stream()
                           .map(this::toOrderItem)
                           .collect(Collectors.toSet());
    }

    OrderItemEntity toOrderItem(OrderItemDto orderItemDto) {
        return OrderItemEntity.builder()
                              .withId(orderItemDto.id())
                              .withProductId(orderItemDto.productId())
                              .withQuantity(orderItemDto.quantity())
                              .withPrice(orderItemDto.price())
                              .build();
    }
}