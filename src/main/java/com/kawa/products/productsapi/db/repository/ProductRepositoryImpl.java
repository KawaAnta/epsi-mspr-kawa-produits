package com.kawa.products.productsapi.db.repository;

import com.kawa.products.productsapi.db.models.ProductDb;
import com.kawa.products.productsapi.db.port.mapper.ProductMapper;
import com.kawa.products.productsapi.domain.ports.ProductRepository;
import com.kawa.products.productsapi.domain.service.product.dto.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implémentation du repository de la couche domaine.
 * Elle permet d'implémenter les méthodes assurant les opérations métier - DB.
 * ELle implémente les mapper assurant la transformation des objets DB - DTO.
 */
@Component
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductDbRepository dbRepository;
    private final ProductMapper mapper;

    @Override
    @Transactional
    public List<Product> getAll() {
        List<ProductDb> products = dbRepository.findAll();
        return products.stream().map(mapper::mapToDomain).toList();
    }

    @Override
    public Product getById(Long id) {
        ProductDb customerDb = dbRepository.findById(id).orElse(null);
        return mapper.mapToDomain(customerDb);
    }

    @Override
    public void deleteById(Long id) {
        dbRepository.deleteById(id);
    }

    @Override
    public Product save(Product product) {
        return mapper.mapToDomain(dbRepository.save(mapper.mapFromDomain(product)));
    }
}
