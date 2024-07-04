package com.kawa.products.productsapi.domain.service.product;

import com.kawa.products.productsapi.domain.ports.ProductRepository;
import com.kawa.products.productsapi.domain.service.product.dto.Product;
import com.kawa.products.productsapi.dto.OrderMessageDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository mockProductRepository;

    private ProductServiceImpl productServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        productServiceImplUnderTest = new ProductServiceImpl(mockProductRepository);
    }

    @Test
    void testGetAll() {
        // GIVEN
        List<Product> products = List.of(Mockito.mock(Product.class));
        when(mockProductRepository.getAll()).thenReturn(products);

        // WHEN
        final List<Product> result = productServiceImplUnderTest.getAll();

        // THEN
        assertThat(result).isEqualTo(products);
    }

    @Test
    void testGetAll_ProductRepositoryReturnsNoItems() {
        // GIVEN
        when(mockProductRepository.getAll()).thenReturn(Collections.emptyList());

        // WHEN
        final List<Product> result = productServiceImplUnderTest.getAll();

        // THEN
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetById() {
        // GIVEN
        Product product = Mockito.mock(Product.class);
        product.setId(0L);

        when(mockProductRepository.getById(0L)).thenReturn(product);

        // WHEN
        final Product result = productServiceImplUnderTest.getById(0L);

        // THEN
        assertThat(result).isEqualTo(product);
    }

    @Test
    void testDeleteById() {
        // WHEN
        productServiceImplUnderTest.deleteById(0L);

        // THEN
        verify(mockProductRepository).deleteById(0L);
    }

    @Test
    void testSave() {
        // GIVEN
        final Product product = Product.builder().build();
        when(mockProductRepository.save(any(Product.class))).thenReturn(product);

        // WHEN
        final Product result = productServiceImplUnderTest.save(product);

        // THEN
        assertThat(result).isEqualTo(product);
    }

    @Test
    void testReduceStock_SufficientStock() {
        // GIVEN
        Product product = new Product();
        product.setId(1L);
        product.setStock(10L);

        OrderMessageDTO orderMessageDTO = new OrderMessageDTO();
        orderMessageDTO.setProductIds(List.of(1L));

        when(mockProductRepository.getById(1L)).thenReturn(product);
        when(mockProductRepository.save(any(Product.class))).thenReturn(product);

        // WHEN
        productServiceImplUnderTest.reduceStock(orderMessageDTO);

        // THEN
        verify(mockProductRepository).getById(1L);
        verify(mockProductRepository).save(product);
        assertThat(product.getStock()).isEqualTo(9);
    }

    @Test
    void testReduceStock_InsufficientStock() {
        // GIVEN
        Product product = new Product();
        product.setId(1L);
        product.setStock(0L);

        OrderMessageDTO orderMessageDTO = new OrderMessageDTO();
        orderMessageDTO.setProductIds(List.of(1L));

        when(mockProductRepository.getById(1L)).thenReturn(product);

        // WHEN / THEN
        assertThatThrownBy(() -> productServiceImplUnderTest.reduceStock(orderMessageDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Rupture de stock.");

        verify(mockProductRepository).getById(1L);
        verify(mockProductRepository, never()).save(any(Product.class));
    }
}
