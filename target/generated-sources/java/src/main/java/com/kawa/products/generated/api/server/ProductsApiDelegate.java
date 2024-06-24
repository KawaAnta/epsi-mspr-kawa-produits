package com.kawa.products.generated.api.server;

import com.kawa.products.generated.api.model.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link ProductsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public interface ProductsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /internal/api/v1/products : Ajoute un nouveau produit
     *
     * @param productDto  (required)
     * @return Produit ajouté avec succès (status code 201)
     *         or Mauvaise requête. (status code 400)
     *         or Erreur interne du serveur (status code 500)
     * @see ProductsApi#createProduct
     */
    default ResponseEntity<String> createProduct(ProductDto productDto) throws Exception {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /internal/api/v1/products/{id} : Supprimer un produit à partir de son identifiant
     *
     * @param id ID du produit à supprimer (required)
     * @return ID invalide (status code 400)
     * @see ProductsApi#deleteProductById
     */
    default ResponseEntity<Void> deleteProductById(Long id) throws Exception {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /internal/api/v1/products : Récupérer tous les produits
     *
     * @return Succès. Renvoie la liste de tous les produits. (status code 200)
     *         or Mauvaise requête (status code 400)
     *         or Produits non retrouvables (status code 404)
     *         or Erreur interne du serveur (status code 500)
     * @see ProductsApi#getAllProducts
     */
    default ResponseEntity<List<ProductDto>> getAllProducts() throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"color\" : \"color\", \"price\" : \"price\", \"name\" : \"name\", \"description\" : \"description\", \"stock\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /internal/api/v1/products/{id} : Récupérer un produit à partir de son identifiant
     *
     * @param id ID du produit à retrouver (required)
     * @return Succès. Renvoie le produit ayant l&#39;identifiant concerné. (status code 200)
     *         or ID invalide (status code 400)
     *         or Produit non retrouvable (status code 404)
     * @see ProductsApi#getProductById
     */
    default ResponseEntity<ProductDto> getProductById(Long id) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"color\" : \"color\", \"price\" : \"price\", \"name\" : \"name\", \"description\" : \"description\", \"stock\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /internal/api/v1/products/{id} : Modifie un produit
     *
     * @param id ID du produit à retrouver (required)
     * @param productDto  (optional)
     * @return Produit modifié avec succès (status code 200)
     *         or Mauvaise requête. (status code 400)
     *         or Erreur interne du serveur (status code 500)
     * @see ProductsApi#updateProduct
     */
    default ResponseEntity<Void> updateProduct(Long id,
        ProductDto productDto) throws Exception {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
