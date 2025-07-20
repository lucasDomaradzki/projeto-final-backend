package com.domaradzki.projeto_final_backend.service;

import com.domaradzki.projeto_final_backend.model.dto.SchoolDTO;

import java.util.List;

public interface ISchoolService {

    List<SchoolDTO> findSchoolsAndGrades();

}
