package com.marjoz.modulith.customer;

import com.marjoz.modulith.customer.dto.CustomerDto;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerFacade {

    private static final Long LOYALTY_POINTS = 10L;

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    CustomerFacade(final CustomerRepository customerRepository,
                   final CustomerMapper customerMapper) {

        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDto> findAll() {
        return customerRepository.findAll().stream()
                                           .map(customerMapper::toDto)
                                           .collect(Collectors.toList());
    }

    public void addLoyaltyPoints(Long customerId) {
        var customer = customerMapper.toEntity(findById(customerId));
        customer = customer.addLoyaltyPoints(LOYALTY_POINTS);

        save(customerMapper.toDto(customer));
    }

    public CustomerDto findById(Long id) {
        return customerMapper.toDto(customerRepository.findById(id));
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public CustomerDto save(CustomerDto customerDto) {
        var savedCustomer = customerRepository.save(customerMapper.toEntity(customerDto));
        return customerMapper.toDto(savedCustomer);
    }
}