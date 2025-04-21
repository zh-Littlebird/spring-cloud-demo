package com.hugo.product.service.impl;

import com.hugo.product.bean.Product;
import com.hugo.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProduct(Long id) {
        Product product = new Product();
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(100));
        product.setProductName("苹果-" + product);
        product.setNum(2);
        return product;

    }
}
