package com.webkart.microservice.product.service;

import com.webkart.microservice.product.dto.ProductRequest;
import com.webkart.microservice.product.dto.ProductResponse;
import com.webkart.microservice.product.model.Product;
import com.webkart.microservice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        // Implementation for creating a product will go here
        Product product = Product.builder().name(productRequest.name()).description(productRequest.description()).price(productRequest.price()).build();
        productRepository.save(product);
        log.info("Product {} is created", product.getName());
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        log.info("Getting All Products");
        return productRepository.findAll().stream().map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice())).toList();
    }
}
