package com.domaradzki.projeto_final_backend.service;

import com.domaradzki.projeto_final_backend.exception.BackendMainException;
import com.domaradzki.projeto_final_backend.model.dto.ProductDTO;

public interface IProductService {

    ProductDTO saveProduct(ProductDTO product) throws BackendMainException;

}
