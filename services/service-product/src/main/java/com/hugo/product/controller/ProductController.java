package com.hugo.product.controller;

import com.hugo.bean.product.Product;
import com.hugo.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/product")
@RestController
public class ProductController {
    @Autowired
    public ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        log.info("收到请求");
        Product product = productService.getProduct(id);
        return product;
    }
}
