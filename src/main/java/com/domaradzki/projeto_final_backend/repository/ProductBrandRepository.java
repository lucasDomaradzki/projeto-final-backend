package com.domaradzki.projeto_final_backend.repository;

import com.domaradzki.projeto_final_backend.model.ProductBrand;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ProductBrandRepository extends JpaRepository<ProductBrand, Long> {

    ProductBrand findByName(@NotNull String name);

}
