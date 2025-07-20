package com.domaradzki.projeto_final_backend.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@JsonInclude(NON_NULL)
public class ProductListDetailedProduct {

    private ProductDTO product;
    private Integer quantity;

}
