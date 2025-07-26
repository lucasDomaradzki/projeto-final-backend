package com.domaradzki.projeto_final_backend.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {

    private String id;
    @NotNull
    private String name;
    @NotNull
    private String productBrand;
    @NotNull
    private String description;
    private String theme;
    private String color;
    private String dimensions;
    @NotNull
    private Long weight;
    @NotNull
    private BigDecimal supplierPrice;
    @NotNull
    private BigDecimal finalPrice;
    private boolean specialPrice;
    private BigDecimal specialPriceAmount;
    @NotNull
    private String productCategory;

}
