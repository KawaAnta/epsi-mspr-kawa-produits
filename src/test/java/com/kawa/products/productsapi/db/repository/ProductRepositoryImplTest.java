package com.kawa.products.productsapi.db.repository;

import com.kawa.products.productsapi.db.models.ProductDb;
import com.kawa.products.productsapi.db.port.mapper.ProductMapper;
import com.kawa.products.productsapi.domain.service.product.dto.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest {

    @Mock
    private ProductDbRepository mockDbRepository;
    @Mock
    private ProductMapper mockMapper;

    private ProductRepositoryImpl productRepositoryImplUnderTest;

    @BeforeEach
    void setUp() {
        productRepositoryImplUnderTest = new ProductRepositoryImpl(mockDbRepository, mockMapper);
    }

    @Test
    void testGetAll() {
        // GIVEN
        final ProductDb productDb = new ProductDb();
        productDb.setId(0L);
        productDb.setName("name");
        productDb.setPrice("price");
        productDb.setDescription("description");
        productDb.setColor("color");
        final List<ProductDb> productDbs = List.of(productDb);
        when(mockDbRepository.findAll()).thenReturn(productDbs);

        when(mockMapper.mapToDomain(any(ProductDb.class))).thenReturn(Product.builder().build());

        // WHEN
        final List<Product> result = productRepositoryImplUnderTest.getAll();

        // THEN
        assertThat(result).hasSize(1);
    }

    @Test
    void testGetAll_ProductDbRepositoryReturnsNoItems() {
        // GIVEN
        when(mockDbRepository.findAll()).thenReturn(Collections.emptyList());

        // WHEN
        final List<Product> result = productRepositoryImplUnderTest.getAll();

        // THEN
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetById() {
        // GIVEN
        final ProductDb productDb1 = new ProductDb();
        productDb1.setId(0L);
        productDb1.setName("name");
        productDb1.setPrice("price");
        productDb1.setDescription("description");
        productDb1.setColor("color");

        final Product product1 = new Product();
        product1.setId(0L);

        final Optional<ProductDb> productDb = Optional.of(productDb1);
        when(mockDbRepository.findById(0L)).thenReturn(productDb);
        when(mockMapper.mapToDomain(any(ProductDb.class))).thenReturn(product1);

        // WHEN
        final Product result = productRepositoryImplUnderTest.getById(0L);

        // THEN
        assertThat(result).isEqualTo(product1);
    }

    @Test
    void testGetById_ProductDbRepositoryReturnsAbsent() {
        // GIVEN
        lenient().when(mockDbRepository.findById(0L)).thenReturn(Optional.empty());
        lenient().when(mockMapper.mapToDomain(any(ProductDb.class))).thenReturn(Product.builder().build());

        // WHEN
        final Product result = productRepositoryImplUnderTest.getById(0L);

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testDeleteById() {
        // WHEN
        productRepositoryImplUnderTest.deleteById(0L);

        // THEN
        verify(mockDbRepository).deleteById(0L);
    }

    @Test
    void testSave() {
        // GIVEN
        final ProductDb productDb = new ProductDb();
        productDb.setId(0L);
        productDb.setName("name");
        productDb.setPrice("price");
        productDb.setDescription("description");
        productDb.setColor("color");
        lenient().when(mockMapper.mapFromDomain(any(Product.class))).thenReturn(productDb);

        Product product = mockMapper.mapToDomain(productDb);

        lenient().when(mockMapper.mapToDomain(any(ProductDb.class))).thenReturn(product);

        // WHEN
        final Product result = productRepositoryImplUnderTest.save(product);

        // THEN
        assertThat(result).isEqualTo(product);
    }
}
