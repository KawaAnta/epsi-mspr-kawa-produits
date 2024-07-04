package com.kawa.products.productsapi.db.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Classe java / DB représentante de la table produit / product
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCTS")
public class ProductDb {
    @Id
    @Column(name = "PRODUCT_ID")
    @SequenceGenerator(name = "customerIdGenerator",
            sequenceName = "PRODUCT_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productIdGenerator")
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRODUCT_PRICE")
    private String price;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String description;

    @Column(name = "PRODUCT_COLOR")
    private String color;

    @Column(name = "PRODUCT_CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "PRODUCT_STOCK")
    private Long stock;
}
