package com.dailycodebuffer.CloudGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/orderServiceFallBack")
    public String orderServiceFallback() {
        return "Order Service is down!";
    }

    @GetMapping("/paymentServiceFallBack")
    public String paymentServiceFallback() {
        return "Payment Service is down!";
    }

    @GetMapping("/productServiceFallBack")
    public String productServiceFallback() {
        return "Product Service is down!";
    }

}
