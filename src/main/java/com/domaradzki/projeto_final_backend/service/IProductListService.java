package com.domaradzki.projeto_final_backend.service;

import com.domaradzki.projeto_final_backend.exception.BackendMainException;
import com.domaradzki.projeto_final_backend.model.dto.ProductListDTO;

import java.util.List;

public interface IProductListService {
    List<ProductListDTO> findProductListsBySchoolUUIDAndGradeUUID(String schoolUUID, String gradeUUID) throws BackendMainException;
    ProductListDTO findProductListDetailsByProductListUUID(String productListUUID) throws BackendMainException;
}
