package com.domaradzki.projeto_final_backend.repository;

import com.domaradzki.projeto_final_backend.model.ProductCategory;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    ProductCategory findByName(@NotNull String productCategory);

}
