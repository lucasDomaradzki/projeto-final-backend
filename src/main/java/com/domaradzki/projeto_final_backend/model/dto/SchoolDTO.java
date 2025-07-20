package com.domaradzki.projeto_final_backend.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SchoolDTO {

    private String schoolId;
    private String name;
    private List<GradeDTO> grades;

}
