package com.marjoz.modulith.notification;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
class NotificationModuleConfiguration {

    @Bean
    NotificationFacade notificationFacade() {
        return new NotificationFacade();
    }
}