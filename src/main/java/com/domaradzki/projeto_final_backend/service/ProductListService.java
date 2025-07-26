package com.domaradzki.projeto_final_backend.service;

import com.domaradzki.projeto_final_backend.exception.BackendMainException;
import com.domaradzki.projeto_final_backend.exception.NotFoundErrorException;
import com.domaradzki.projeto_final_backend.model.Product;
import com.domaradzki.projeto_final_backend.model.ProductList;
import com.domaradzki.projeto_final_backend.model.dto.ProductDTO;
import com.domaradzki.projeto_final_backend.model.dto.ProductListDTO;
import com.domaradzki.projeto_final_backend.model.dto.ProductListDetailedProduct;
import com.domaradzki.projeto_final_backend.repository.ProductListRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProductListService implements IProductListService {

    private static final String COMMON_ERROR_MESSAGE = "Could not find product list for id: {}";

    private ProductListRepository productListRepository;

    @Override
    public List<ProductListDTO> findProductListsBySchoolUUIDAndGradeUUID(String schoolUUID, String gradeUUID) throws BackendMainException {
        log.info("Searching for school: {} and grade: {}", schoolUUID, gradeUUID);
        return productListRepository.findBySchool_UuidAndGrade_Uuid(schoolUUID, gradeUUID)
                .stream().map(productList -> {
                    ProductListDTO productListDTO = new ProductListDTO();
                    productListDTO.setId(productList.getUuid());
                    productListDTO.setSchoolId(productList.getSchool().getUuid());
                    productListDTO.setGradeId(productList.getGrade().getUuid());
                    productListDTO.setName(productList.getName());
                    productListDTO.setPrice(productList.getPrice());
                    productListDTO.setDescription(productList.getDescription());
                    return productListDTO;
                }).collect(Collectors.toList());
    }



    @Override
    public ProductListDTO findProductListDetailsByProductListUUID(String productListUUID) throws BackendMainException {
        return productListRepository.findByuuid(productListUUID).map(this::mapProductListDetailedDTO)
                .orElseThrow(() -> new NotFoundErrorException("Could not find product list for id: {}", productListUUID));
    }

    private ProductListDTO mapProductListDTO(ProductList productList) {
        ProductListDTO productListDTO = new ProductListDTO();
        productListDTO.setId(productList.getUuid());
        productListDTO.setSchoolId(productList.getSchool().getUuid());
        productListDTO.setGradeId(productList.getGrade().getUuid());
        productListDTO.setName(productList.getName());
        productListDTO.setPrice(productList.getPrice());
        productListDTO.setDescription(productList.getDescription());
        return productListDTO;
    }

    private ProductListDTO mapProductListDetailedDTO(ProductList productList) {
        ProductListDTO productListDTO = mapProductListDTO(productList);
        List<ProductListDetailedProduct> detailedProducts = productList.getProductListItems().stream().map(productListItem -> {
            Product product = productListItem.getProduct();
            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setId(product.getUuid());

            ProductListDetailedProduct detailedProduct = new ProductListDetailedProduct();
            detailedProduct.setProduct(productDTO);
            detailedProduct.setQuantity(productListItem.getQuantity());
            return detailedProduct;
        }).collect(Collectors.toList());
        productListDTO.setProducts(detailedProducts);
        return productListDTO;
    }

}
