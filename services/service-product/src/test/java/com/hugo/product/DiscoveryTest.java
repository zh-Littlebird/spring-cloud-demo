package com.hugo.product;

import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest
public class DiscoveryTest {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    void nacosServiceDiscoveryTest() throws NacosException {
        for (String service : nacosServiceDiscovery.getServices()) {
            System.out.println("Service: " + service);
            List<ServiceInstance> instances = nacosServiceDiscovery.getInstances(service);
            // 获取ip + 端口
            for (ServiceInstance instance : instances) {
                System.out.println("ip: " + instance.getHost());
                System.out.println("port: " + instance.getPort());
            }
        }
    }


    @Test
    void discoveryClientTest() {
        for (String service : discoveryClient.getServices()) {
            System.out.println("Service: " + service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            // 获取ip + 端口
            for (ServiceInstance instance : instances) {
                System.out.println("ip: " + instance.getHost());
                System.out.println("port: " + instance.getPort());
            }
        }
    }
}
