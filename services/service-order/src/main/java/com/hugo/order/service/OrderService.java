package com.hugo.order.service;

import com.hugo.order.bean.Order;

public interface OrderService {
    Order createOrder(Long userId, Long productId);
}
