package com.hugo.order.service.impl;

import com.hugo.order.bean.Order;
import com.hugo.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order createOrder(Long userId, Long productId) {
        Order order = new Order();
        // TODO 总金额
        order.setTotalAmount(new BigDecimal("0"));
        order.setId(1L);
        order.setUserId(userId);
        order.setNickName("Hugo");
        // TODO 远程查询商品列表
        order.setProductList(null);
        return order;
    }
}
