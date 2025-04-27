package com.hugo.order.controller;

import com.hugo.bean.order.Order;
import com.hugo.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Value("${order.timeout}")
    private String orderTimeout;
    @Value("${order.auto-confirm}")
    private String orderAutoConfirm;

    @GetMapping("/config")
    public String config() {
        return "order.timeout: " + orderTimeout + ", order.auto-confirm: " + orderAutoConfirm;
    }

    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        Order order = orderService.createOrder(userId, productId);
        return order;
    }
}
