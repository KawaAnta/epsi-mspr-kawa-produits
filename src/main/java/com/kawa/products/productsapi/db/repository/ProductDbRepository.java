package com.kawa.products.productsapi.db.repository;

import com.kawa.products.productsapi.db.models.ProductDb;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository de la couche DB, il assure les connexions avec la base de données directement.
 */
@Repository
public interface ProductDbRepository extends CrudRepository<ProductDb, Long> {

    /**
     * Lecture de tous les produits présents en BDD.
     * @return la liste de tous les produits.
     */
    @NonNull
    List<ProductDb> findAll();
}
