package com.kawa.products.productsapi.domain.service.product;

import com.kawa.products.productsapi.domain.service.product.dto.Product;
import com.kawa.products.productsapi.dto.OrderMessageDTO;

import java.util.List;

/**
 * Service des produits, contient toutes les méthodes de traitement des données produits en BDD.
 */
public interface ProductService {

    /**
     * Lecture de tous les produits présents en BDD.
     * @return la liste de tous les produits.
     */
    List<Product> getAll();

    /**
     * Lecture d'un produit à partir de son identifiant.
     * @param id identifiant du produit.
     * @return un produit.
     */
    Product getById(Long id);

    /**
     * Supprime un produit à travers son identifiant.
     * @param id identifiant du produit à supprimer.
     */
    void deleteById(Long id);

    /**
     * Enregistre ou modifie un produit en BDD.
     * @param product produit.
     */
    Product save(Product product);

    /**
     * Réduit le stock des produits cités dans des commandes.
     * @param orderMessageDTO objet des produits.
     */
    void reduceStock(OrderMessageDTO orderMessageDTO);

}
