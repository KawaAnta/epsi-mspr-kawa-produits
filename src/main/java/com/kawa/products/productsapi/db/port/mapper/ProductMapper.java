package com.kawa.products.productsapi.db.port.mapper;


import com.kawa.products.productsapi.db.models.ProductDb;
import com.kawa.products.productsapi.domain.service.product.dto.Product;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

/**
 * Classe assurant les mapping DTO -> DB et DB -> DTO pour les objets Product.
 */
@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ProductMapper {

    /**
     * Transforme l'objet DB en DTO.
     *
     * @param entity l'objet DB
     * @return l'objet DTO
     */
    Product mapToDomain(ProductDb entity);

    /**
     * Transforme l'objet DTO en DB
     *
     * @param dto l'objet DTO
     * @return l'objet DB
     */
    ProductDb mapFromDomain(Product dto);

    /**
     * Tranforme une liste d'objets DB en liste d'objets DTO
     *
     * @param entities liste des objets DB
     * @return liste des objets DTO
     */
    List<Product> mapListToDomain(List<ProductDb> entities);
}
