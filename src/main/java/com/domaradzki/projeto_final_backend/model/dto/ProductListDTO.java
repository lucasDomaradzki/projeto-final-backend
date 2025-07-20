package com.domaradzki.projeto_final_backend.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@JsonInclude(NON_NULL)
public class ProductListDTO {

    private String id;
    private String schoolId;
    private String gradeId;
    private BigDecimal price;
    private String name;
    private String description;
    private List<ProductListDetailedProduct> products;

}
