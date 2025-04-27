package com.hugo.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@SpringBootTest
public class LoadBalancerTest {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Test
    void test(){
        ServiceInstance instance = loadBalancerClient.choose("service-order");
        System.out.println("choose:" + instance.getHost() + ":" + instance.getPort());
        ServiceInstance instance2 = loadBalancerClient.choose("service-order");
        System.out.println("choose:" + instance2.getHost() + ":" + instance2.getPort());
        ServiceInstance instance3 = loadBalancerClient.choose("service-order");
        System.out.println("choose:" + instance3.getHost() + ":" + instance3.getPort());
    }
}
