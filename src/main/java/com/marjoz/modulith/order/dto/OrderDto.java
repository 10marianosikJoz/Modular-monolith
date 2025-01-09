package com.marjoz.modulith.order.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record OrderDto(Long id,
                       Long customerId,
                       LocalDate orderDate,
                       BigDecimal totalPrice,
                       Set<OrderItemDto> orderItems) {

    public OrderDto(Long id,
                    Long customerId,
                    LocalDate orderDate,
                    BigDecimal totalPrice,
                    Set<OrderItemDto> orderItems) {

        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderItems = Set.copyOf(orderItems);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private Long customerId;
        private LocalDate orderDate;
        private BigDecimal totalPrice;
        private Set<OrderItemDto> orderItems;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withCustomerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder withTotalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder withOrderItems(Set<OrderItemDto> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public OrderDto build() {
            return new OrderDto(id, customerId, orderDate, totalPrice, orderItems);
        }
    }
}