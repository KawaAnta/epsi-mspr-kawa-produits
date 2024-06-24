package com.kawa.products.productsapi.domain.service.product.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Classe java / DTO représentante de l'objet produit / product
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String price;
    private String description;
    private String color;
    private LocalDateTime createdAt;
    private Long stock;
}
