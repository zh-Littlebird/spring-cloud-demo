package com.hugo.order.service.impl;

import com.hugo.bean.order.Order;
import com.hugo.bean.product.Product;
import com.hugo.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Override
    public Order createOrder(Long userId, Long productId) {
        Product product = getProductFromRemoteWithLoadBalancer(productId);
        Order order = new Order();
        // TODO 总金额
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setId(1L);
        order.setUserId(userId);
        order.setNickName("Hugo");
        // TODO 远程查询商品列表
        order.setProductList(List.of(product));
        return order;
    }

    @Override
    public Product getProductFromRemote(Long productId) {
        // 1、获取商品服务所在的所有服务器ip和端口
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);
        // 远程url地址
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("远程请求：" + url);
        // 2、远程调用商品服务
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    @Override
    public Product getProductFromRemoteWithLoadBalancer(Long productId) {
        // 1、获取商品服务所在的所有服务器ip和端口
        ServiceInstance instance = loadBalancerClient.choose("service-product");
        // 远程url地址
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("远程请求：" + url);
        // 2、远程调用商品服务
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }
}
