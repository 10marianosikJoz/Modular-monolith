package com.marjoz.modulith.customer;

public class CustomerModuleTestConfiguration {

    public CustomerFacade customerFacade() {
        return new CustomerFacade(customerRepository(), new CustomerMapper());
    }

    CustomerRepository customerRepository() {
        return new CustomerRepository();
    }
}