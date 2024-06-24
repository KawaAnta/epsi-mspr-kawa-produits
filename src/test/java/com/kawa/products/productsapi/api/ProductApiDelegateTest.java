package com.kawa.products.productsapi.api;

import com.kawa.products.generated.api.model.ProductDto;
import com.kawa.products.productsapi.domain.service.product.ProductService;
import com.kawa.products.productsapi.domain.service.product.dto.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductApiDelegateTest {

    @Mock
    private ProductService mockProductService;

    private ProductApiDelegate productApiDelegateUnderTest;

    @BeforeEach
    void setUp() {
        productApiDelegateUnderTest = new ProductApiDelegate(mockProductService);
    }

    @Test
    void testGetAllProducts() {
        // Setup
        final ProductDto productDto = new ProductDto();
        productDto.setName("name");
        productDto.setPrice("price");
        productDto.setDescription("description");
        productDto.setColor("color");
        productDto.setStock(0L);
        final ResponseEntity<List<ProductDto>> expectedResult = new ResponseEntity<>(List.of(productDto),
                HttpStatus.OK);

        // Configure ProductService.getAll(...).
        final List<Product> products = List.of(Product.builder()
                .id(0L)
                .name("name")
                .price("price")
                .description("description")
                .color("color")
                .createdAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .stock(0L)
                .build());
        when(mockProductService.getAll()).thenReturn(products);

        // Run the test
        final ResponseEntity<List<ProductDto>> result = productApiDelegateUnderTest.getAllProducts();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllProducts_ProductServiceReturnsNoItems() {
        // GIVEN
        when(mockProductService.getAll()).thenReturn(Collections.emptyList());

        // WHEN
        final ResponseEntity<List<ProductDto>> result = productApiDelegateUnderTest.getAllProducts();

        // THEN
        assertThat(result).isEqualTo(ResponseEntity.ok(Collections.emptyList()));
    }

    @Test
    void testGetProductById() {
        // GIVEN
        final ProductDto productDto = new ProductDto();
        productDto.setName("name");
        productDto.setPrice("price");
        productDto.setDescription("description");
        productDto.setColor("color");
        productDto.setStock(0L);
        final ResponseEntity<ProductDto> expectedResult = new ResponseEntity<>(productDto, HttpStatus.OK);

        final Product product = Product.builder()
                .id(0L)
                .name("name")
                .price("price")
                .description("description")
                .color("color")
                .createdAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .stock(0L)
                .build();
        when(mockProductService.getById(0L)).thenReturn(product);

        // WHEN
        final ResponseEntity<ProductDto> result = productApiDelegateUnderTest.getProductById(0L);

        // THEN
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDeleteProductById() {
        // GIVEN
        final Product product = Product.builder()
                .id(0L)
                .name("name")
                .price("price")
                .description("description")
                .color("color")
                .createdAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .stock(0L)
                .build();
        when(mockProductService.getById(0L)).thenReturn(product);

        // WHEN
        final ResponseEntity<Void> result = productApiDelegateUnderTest.deleteProductById(0L);

        // THEN
        verify(mockProductService).deleteById(0L);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void testCreateProduct() {
        // GIVEN
        final ProductDto productDto = new ProductDto("name", "price", "description", "color", 0L);

        final Product product = Product.builder()
                .id(0L)
                .name("name")
                .price("price")
                .description("description")
                .color("color")
                .createdAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .stock(0L)
                .build();
        when(mockProductService.save(any(Product.class))).thenReturn(product);

        // WHEN
        final ResponseEntity<String> result = productApiDelegateUnderTest.createProduct(productDto);

        // THEN
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void testUpdateProduct() {
        // GIVEN
        final ProductDto productDto = new ProductDto("name", "price", "description", "color", 0L);

        final Product product = Product.builder()
                .id(0L)
                .name("name")
                .price("price")
                .description("description")
                .color("color")
                .createdAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .stock(0L)
                .build();
        when(mockProductService.getById(0L)).thenReturn(product);

        final List<Product> products = List.of(Product.builder()
                .id(0L)
                .name("name")
                .price("price")
                .description("description")
                .color("color")
                .createdAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .stock(0L)
                .build());
        when(mockProductService.getAll()).thenReturn(products);

        // WHEN
        final ResponseEntity<Void> result = productApiDelegateUnderTest.updateProduct(0L, productDto);

        // THEN
        verify(mockProductService).save(any(Product.class));
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testUpdateProduct_ProductServiceGetAllReturnsNoItems() {
        // GIVEN
        final ProductDto productDto = new ProductDto("name", "price", "description", "color", 0L);

        final Product product = Product.builder()
                .id(0L)
                .name("name")
                .price("price")
                .description("description")
                .color("color")
                .createdAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .stock(0L)
                .build();
        when(mockProductService.getById(0L)).thenReturn(product);

        when(mockProductService.getAll()).thenReturn(Collections.emptyList());

        // WHEN
        final ResponseEntity<Void> result = productApiDelegateUnderTest.updateProduct(0L, productDto);

        // THEN
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
