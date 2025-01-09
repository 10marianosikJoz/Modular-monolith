package com.marjoz.modulith.order;

import com.marjoz.modulith.customer.CustomerFacade;
import com.marjoz.modulith.customer.CustomerModuleTestConfiguration;
import com.marjoz.modulith.product.ProductFacade;
import com.marjoz.modulith.product.ProductModuleTestConfiguration;

class OrderModuleTestConfiguration {

    private final OrderModuleConfiguration orderModuleConfiguration = new OrderModuleConfiguration();
    private final ProductModuleTestConfiguration productModuleTestConfiguration = new ProductModuleTestConfiguration();
    private final CustomerModuleTestConfiguration customerModuleTestConfiguration = new CustomerModuleTestConfiguration();

    OrderFacade orderFacade() {
        return new OrderFacade(orderRepository(), productFacade(), customerFacade(), new OrderMapper());
    }

    OrderRepository orderRepository() {
        return orderModuleConfiguration.orderRepository();
    }

    ProductFacade productFacade() {
        return productModuleTestConfiguration.productFacade();
    }

    CustomerFacade customerFacade() {
        return customerModuleTestConfiguration.customerFacade();
    }
}