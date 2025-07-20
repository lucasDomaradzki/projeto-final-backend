package com.domaradzki.projeto_final_backend.service;

import com.domaradzki.projeto_final_backend.model.School;
import com.domaradzki.projeto_final_backend.model.dto.GradeDTO;
import com.domaradzki.projeto_final_backend.model.dto.SchoolDTO;
import com.domaradzki.projeto_final_backend.repository.SchoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SchoolService implements ISchoolService {

    private SchoolRepository schoolRepository;

    @Override
    public List<SchoolDTO> findSchoolsAndGrades() {
        List<School> all = schoolRepository.findAll();
        return all.stream().map(school -> {
            SchoolDTO schoolDTO = new SchoolDTO();
            schoolDTO.setName(school.getName());
            schoolDTO.setSchoolId(school.getUuid());
            schoolDTO.setGrades(
                school.getGrades().stream().map(grade -> {
                    GradeDTO gradeDTO = new GradeDTO();
                    gradeDTO.setGradeId(grade.getUuid());
                    gradeDTO.setName(grade.getName());
                    return gradeDTO;
                }).collect(Collectors.toList())
            );

            return schoolDTO;
        }).collect(Collectors.toList());
    }
}
