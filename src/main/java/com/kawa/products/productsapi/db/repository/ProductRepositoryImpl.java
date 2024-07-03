package com.kawa.products.productsapi.db.repository;

import com.kawa.products.productsapi.db.models.ProductDb;
import com.kawa.products.productsapi.db.port.mapper.ProductMapper;
import com.kawa.products.productsapi.domain.ports.ProductRepository;
import com.kawa.products.productsapi.domain.service.product.dto.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

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
    static Logger logger = Logger.getLogger(ProductRepositoryImpl.class.getName());

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
        try {
            dbRepository.deleteById(id);
        } catch (Exception exception) {
            logger.warning(exception.getMessage());
        }
    }

    @Override
    public Product save(Product product) {
        try {
            return mapper.mapToDomain(dbRepository.save(mapper.mapFromDomain(product)));
        } catch (Exception exception) {
            logger.warning(exception.getMessage());
        }
        return null;
    }
}
