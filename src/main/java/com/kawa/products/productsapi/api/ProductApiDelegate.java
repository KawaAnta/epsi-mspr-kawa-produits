package com.kawa.products.productsapi.api;

import com.kawa.products.generated.api.model.ProductDto;
import com.kawa.products.generated.api.server.ProductsApiDelegate;
import com.kawa.products.productsapi.domain.service.product.ProductService;
import com.kawa.products.productsapi.domain.service.product.dto.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Délégué API des clients.
 * Assure la connexion entre l'API Gateway générale et les micro-services des produits.
 */
@Component
public class ProductApiDelegate implements ProductsApiDelegate {
    private final ProductService productService;

    public ProductApiDelegate(ProductService productService) {
        this.productService = productService;
    }
    
    @Override
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        try {
            final List<Product> productList = productService.getAll();
            final List<ProductDto> productDtoList = new ArrayList<>(productList.size());

            for (final Product product : productList) {
                productDtoList.add(mapToDto(product));
            }

            return ResponseEntity.ok(productDtoList);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<ProductDto> getProductById(Long id) {
        try {
            Product product = productService.getById(id);

            if (product.getId() == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(mapToDto(product));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Void> deleteProductById(Long id) {
        try {
            Product product = productService.getById(id);

            if (product.getId() == null) {
                return ResponseEntity.notFound().build();
            }

            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Override
    public ResponseEntity<String> createProduct(@Valid ProductDto productDto) {
        try {
            Product product = new Product();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setColor(productDto.getColor());
            product.setStock(productDto.getStock());
            product.setCreatedAt(LocalDateTime.now());
            Product savedProduct = productService.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct.getId().toString());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur inattendue.");
        }
    }

    @Override
    public ResponseEntity<Void> updateProduct(Long id, ProductDto productDto) {
        try {
            // Récupérer le produit existant avec l'ID spécifié
            Product product = productService.getById(id);

            // Récupérer la liste de tous les IDs des produits existants
            List<Product> productList = productService.getAll();
            List<Long> ids = new ArrayList<>();
            for (Product existingProduct : productList) {
                ids.add(existingProduct.getId());
            }

            // Vérifier si l'ID spécifié ne correspond à aucun des IDs existants
            if (!ids.contains(id)) {
                return ResponseEntity.notFound().build();
            }

            // Copier les propriétés non null de productDto vers product
            copyProperties(productDto, product);

            // Enregistrer les modifications
            productService.save(product);

            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    /**
     * Vérifie les champs mentionnés dans la requête API pour les modifier dans l'objet produit.
     *
     * @param dto la source
     * @param target l'objet produit en BDD
     */
    private void copyProperties(ProductDto dto, Product target) throws IllegalAccessException {
        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(dto);
            if (value != null) {
                Field targetField;
                try {
                    targetField = target.getClass().getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, value);
                } catch (NoSuchFieldException ignored) {
                }
            }
        }
    }

    @NotNull
    private static ProductDto mapToDto(@NotNull final Product product) {
        return new ProductDto(
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getColor(),
                product.getStock()
        );
    }
}
