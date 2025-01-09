package com.marjoz.modulith.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

record OrderEntity(Long id,
                   Long customerId,
                   LocalDate orderDate,
                   BigDecimal totalPrice,
                   Set<OrderItemEntity> orderItems) {

    OrderEntity(Long id,
                Long customerId,
                LocalDate orderDate,
                BigDecimal totalPrice,
                Set<OrderItemEntity> orderItems) {

        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderItems = Set.copyOf(orderItems);
    }

    OrderEntity addOrderItem(OrderItemEntity item) {
        var newOrderItems = new HashSet<>(this.orderItems);
        newOrderItems.add(item);

        return new OrderEntity(this.id, this.customerId, this.orderDate, this.totalPrice, newOrderItems);
    }

    static Builder builder() {
        return new Builder();
    }

    static class Builder {

        private Long id;
        private Long customerId;
        private LocalDate orderDate;
        private BigDecimal totalPrice;
        private Set<OrderItemEntity> orderItems;

        Builder withId(Long id) {
            this.id = id;
            return this;
        }

        Builder withCustomerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        Builder withOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        Builder withTotalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        Builder withOrderItems(Set<OrderItemEntity> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        OrderEntity build() {
            return new OrderEntity(id, customerId, orderDate, totalPrice, orderItems);
        }
    }
}