package com.marjoz.modulith.order;

import com.marjoz.modulith.order.exception.OrderDomainException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class OrderRepository {

    private static final Map<Long, OrderEntity> DATABASE = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    void truncate() {
        DATABASE.clear();
    }

    OrderEntity save(OrderEntity orderEntity) {
        if (orderEntity.id() == null) {
            orderEntity = new OrderEntity(ID_GENERATOR.getAndIncrement(),
                                          orderEntity.customerId(),
                                          orderEntity.orderDate(),
                                          orderEntity.totalPrice(),
                                          orderEntity.orderItems());
        }
        DATABASE.put(orderEntity.id(), orderEntity);
        return orderEntity;
    }

    List<OrderEntity> findAll() {
        return List.copyOf(DATABASE.values());
    }

    OrderEntity findById(Long id) {
        var entity = DATABASE.get(id);
        if (entity == null) {
            throw new OrderDomainException("Order with id " + id + " not found");
        }
        return entity;
    }

    void deleteById(Long id) {
        DATABASE.remove(id);
    }
}