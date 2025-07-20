package com.domaradzki.projeto_final_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "school")
public class School {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_focus_id", nullable = false)
    private SchoolFocus schoolFocus;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "active")
    private boolean active;

    @Column(name = "address", length = 60)
    private String address;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades = new ArrayList<>();

    @Column(name = "uuid", nullable = false, unique = true, length = 36)
    private String uuid;

    @PrePersist
    private void beforePersist() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }

}
