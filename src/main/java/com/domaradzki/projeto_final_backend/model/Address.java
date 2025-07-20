package com.domaradzki.projeto_final_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label", nullable = false, length = 30)
    private String label;

    @Column(name = "active")
    private boolean active;

    @Column(name = "isprimary")
    private boolean isprimary;

    @Column(name = "zipcode", nullable = false, length = 8)
    private String zipcode;

    @Column(name = "streetname", nullable = false, length = 30)
    private String streetname;

    @Column(name = "address_number", nullable = false, length = 15)
    private String addressNumber;

    @Column(name = "neighborhood", nullable = false, length = 50)
    private String neighborhood;

    @Column(name = "point_of_reference", nullable = false, length = 50)
    private String pointOfReference;

    @Column(name = "uuid", nullable = false, unique = true, length = 36)
    private String uuid;

    @PrePersist
    private void beforePersist() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }

}
