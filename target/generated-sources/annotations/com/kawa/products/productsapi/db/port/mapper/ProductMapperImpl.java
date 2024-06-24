package com.kawa.products.productsapi.db.port.mapper;

import com.kawa.products.productsapi.db.models.ProductDb;
import com.kawa.products.productsapi.domain.service.product.dto.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T13:59:35+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product mapToDomain(ProductDb entity) {

        Product.ProductBuilder product = Product.builder();

        if ( entity != null ) {
            product.id( entity.getId() );
            product.name( entity.getName() );
            product.price( entity.getPrice() );
            product.description( entity.getDescription() );
            product.color( entity.getColor() );
            product.createdAt( entity.getCreatedAt() );
            product.stock( entity.getStock() );
        }

        return product.build();
    }

    @Override
    public ProductDb mapFromDomain(Product dto) {

        ProductDb productDb = new ProductDb();

        if ( dto != null ) {
            productDb.setId( dto.getId() );
            productDb.setName( dto.getName() );
            productDb.setPrice( dto.getPrice() );
            productDb.setDescription( dto.getDescription() );
            productDb.setColor( dto.getColor() );
            productDb.setCreatedAt( dto.getCreatedAt() );
            productDb.setStock( dto.getStock() );
        }

        return productDb;
    }

    @Override
    public List<Product> mapListToDomain(List<ProductDb> entities) {
        if ( entities == null ) {
            return new ArrayList<Product>();
        }

        List<Product> list = new ArrayList<Product>( entities.size() );
        for ( ProductDb productDb : entities ) {
            list.add( mapToDomain( productDb ) );
        }

        return list;
    }
}
