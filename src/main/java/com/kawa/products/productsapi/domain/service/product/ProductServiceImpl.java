package com.kawa.products.productsapi.domain.service.product;

import com.kawa.products.productsapi.domain.ports.ProductRepository;
import com.kawa.products.productsapi.domain.service.product.dto.Product;
import com.kawa.products.productsapi.dto.OrderMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation du service des produits.
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void reduceStock(OrderMessageDTO orderMessageDTO) {
        List<Long> productIds = orderMessageDTO.getProductIds();
        for (Long productId : productIds) {
            Product product = productRepository.getById(productId);
            if (product.getStock() > 0) {
                product.setStock(product.getStock() - 1);
                productRepository.save(product);
            } else {
                throw new RuntimeException("Rupture de stock.");
            }
        }
    }
}
