package com.dailycodebuffer.OrderService;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class OrderServiceConfig {


    public ServiceInstanceListSupplier supplier() {
        return new TestServiceInstanceListSupplier();
    }
}
