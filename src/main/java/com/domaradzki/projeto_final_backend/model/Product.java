package com.domaradzki.projeto_final_backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 60)
    private String name;

    @OneToOne
    @JoinColumn(name = "product_brand_id", nullable = false)
    private ProductBrand productBrand;

    @Column(name = "description", nullable = false, length = 240)
    private String description;

    @Column(name = "theme", nullable = false, length = 30)
    private String theme;

    @Column(name = "color", nullable = false, length = 20)
    private String color;

    @Column(name = "dimensions", nullable = false, length = 20)
    private String dimensions;

    @Column(name = "weight", nullable = false)
    private Long weight;

    @Column(name = "suppler_price", nullable = false)
    private BigDecimal supplerPrice;

    @Column(name = "final_price", nullable = false)
    private BigDecimal finalPrice;

    @Column(name = "special_price", nullable = false)
    private boolean specialPrice;

    @Column(name = "special_price_amount")
    private BigDecimal specialPriceAmount;

    @OneToOne
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory productCategory;

    @Column(name = "uuid", nullable = false, unique = true, length = 36)
    private String uuid;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductListItem> productListItems = new ArrayList<>();

    @PrePersist
    private void beforePersist() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }

}
