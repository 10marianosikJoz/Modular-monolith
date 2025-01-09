package com.marjoz.modulith.customer;

public class CustomerRepositoryTestApi {

    private final CustomerModuleTestConfiguration customerModuleTestConfiguration = new CustomerModuleTestConfiguration();
    private final CustomerRepository customerRepository = customerModuleTestConfiguration.customerRepository();

    public void truncate() {
        customerRepository.truncate();
    }
}