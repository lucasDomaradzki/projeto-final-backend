package com.domaradzki.projeto_final_backend.service;

import com.domaradzki.projeto_final_backend.exception.BackendMainException;
import com.domaradzki.projeto_final_backend.exception.BadRequestErrorException;
import com.domaradzki.projeto_final_backend.model.Product;
import com.domaradzki.projeto_final_backend.model.ProductBrand;
import com.domaradzki.projeto_final_backend.model.ProductCategory;
import com.domaradzki.projeto_final_backend.model.dto.ProductDTO;
import com.domaradzki.projeto_final_backend.repository.ProductBrandRepository;
import com.domaradzki.projeto_final_backend.repository.ProductCategoryRepository;
import com.domaradzki.projeto_final_backend.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductBrandRepository productBrandRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = BackendMainException.class)
    public ProductDTO saveProduct(ProductDTO product) throws BackendMainException {
        try {
            ProductBrand brand = productBrandRepository.findByName(product.getProductBrand());
            if (brand == null) {
                brand = new ProductBrand();
                brand.setName(product.getProductBrand());
                brand = productBrandRepository.save(brand);
            }

            ProductCategory category = productCategoryRepository.findByName(product.getProductCategory());
            if (category == null) {
                category = new ProductCategory();
                category.setName(product.getProductCategory());
                category = productCategoryRepository.save(category);
            }

            Product entity = getProduct(product, brand, category);
            entity = productRepository.save(entity);
            product.setId(entity.getUuid());
            return product;
        } catch (Exception e) {
            throw new BadRequestErrorException("Failed to save product with error: {0}", e.getMessage());
        }
    }

    /**
     * Prepares Product object to be saved
     * @param product ProductDTO
     * @param brand Brand
     * @param category Category
     * @return Returns saved product
     */
    private static Product getProduct(ProductDTO product, ProductBrand brand, ProductCategory category) {
        Product entity = new Product();
        entity.setName(product.getName());
        entity.setProductBrand(brand);
        entity.setDescription(product.getDescription());
        entity.setTheme(product.getTheme());
        entity.setColor(product.getColor());
        entity.setDimensions(product.getDimensions());
        entity.setWeight(product.getWeight());
        entity.setSupplerPrice(product.getSupplierPrice());
        entity.setFinalPrice(product.getFinalPrice());
        entity.setSpecialPrice(product.isSpecialPrice());
        entity.setSpecialPriceAmount(product.getSpecialPriceAmount());
        entity.setProductCategory(category);
        return entity;
    }

}
