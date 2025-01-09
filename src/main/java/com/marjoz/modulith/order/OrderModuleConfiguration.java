package com.marjoz.modulith.order;

import com.marjoz.modulith.customer.CustomerFacade;
import com.marjoz.modulith.product.ProductFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
class OrderModuleConfiguration {

    @Bean
    OrderFacade orderFacade(OrderRepository orderRepository,
                            ProductFacade productFacade,
                            CustomerFacade customerFacade) {

        return new OrderFacade(orderRepository,
                               productFacade,
                               customerFacade,
                               new OrderMapper());
    }

    @Bean
    OrderRepository orderRepository() {
        return new OrderRepository();
    }
}