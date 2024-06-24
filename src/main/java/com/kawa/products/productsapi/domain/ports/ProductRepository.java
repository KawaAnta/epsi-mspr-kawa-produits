package com.kawa.products.productsapi.domain.ports;

import com.kawa.products.productsapi.domain.service.product.dto.Product;

import java.util.List;

/**
 * Repository de la couche domaine.
 */
public interface ProductRepository {

    /**
     * Lecture de tous les produits renseignés en BDD.
     * @return la liste de tous les produits.
     */
    List<Product> getAll();

    /**
     * Lecture d'un produit par ID.
     * @param id identifiant du produit.
     * @return un produit.
     */
    Product getById(Long id);

    /**
     * Supprime un produit à travers son ID.
     * @param id identifiant du produit.
     */
    void deleteById(Long id);

    /**
     * Enregistre ou modifie un produit en BDD.
     * @param product produit.
     */
    Product save(Product product);
}
