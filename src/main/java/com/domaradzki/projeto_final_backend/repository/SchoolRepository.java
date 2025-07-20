package com.domaradzki.projeto_final_backend.repository;

import com.domaradzki.projeto_final_backend.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {
}
