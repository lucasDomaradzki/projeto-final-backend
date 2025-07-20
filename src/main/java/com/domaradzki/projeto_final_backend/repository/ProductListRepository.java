package com.domaradzki.projeto_final_backend.repository;

import com.domaradzki.projeto_final_backend.model.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductListRepository extends JpaRepository<ProductList, Long> {

    List<ProductList> findBySchool_UuidAndGrade_Uuid(String schoolUUID, String gradeUUID);
    Optional<ProductList> findByuuid(String uuid);

}
