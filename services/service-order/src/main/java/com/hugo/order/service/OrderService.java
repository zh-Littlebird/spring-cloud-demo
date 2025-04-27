package com.hugo.order.service;


import com.hugo.bean.order.Order;
import com.hugo.bean.product.Product;

public interface OrderService {
    Order createOrder(Long userId, Long productId);

    Product getProductFromRemote(Long productId);

    Product getProductFromRemoteWithLoadBalancer(Long productId);
}
