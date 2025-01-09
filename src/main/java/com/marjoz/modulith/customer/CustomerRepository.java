package com.marjoz.modulith.customer;

import com.marjoz.modulith.customer.exception.CustomerDomainException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class CustomerRepository {

    private static final Map<Long, CustomerEntity> DATABASE = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    void truncate() {
        DATABASE.clear();
    }

    List<CustomerEntity> findAll() {
        return List.copyOf(DATABASE.values());
    }

    CustomerEntity save(CustomerEntity customerEntity) {
        if (customerEntity.id() == null) {
            customerEntity = new CustomerEntity(ID_GENERATOR.getAndIncrement(),
                                                customerEntity.name(),
                                                customerEntity.surname(),
                                                customerEntity.email(),
                                                customerEntity.address(),
                                                customerEntity.loyaltyPoints());
        }
        DATABASE.put(customerEntity.id(), customerEntity);
        return customerEntity;
    }

    CustomerEntity findById(Long id) {
        var entity = DATABASE.get(id);
        if (entity == null) {
            throw new CustomerDomainException("Customer with id " + id + " not found.");
        }
        return entity;
    }

    void deleteById(Long id) {
        DATABASE.remove(id);
    }
}