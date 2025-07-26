package com.domaradzki.projeto_final_backend.repository;

import com.domaradzki.projeto_final_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
