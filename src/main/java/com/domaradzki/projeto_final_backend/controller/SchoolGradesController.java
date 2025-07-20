package com.domaradzki.projeto_final_backend.controller;

import com.domaradzki.projeto_final_backend.model.dto.SchoolDTO;
import com.domaradzki.projeto_final_backend.service.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/school")
@Tag(name = "School and Grades", description = "School and Grades API")
@AllArgsConstructor
public class SchoolGradesController {

    private SchoolService schoolService;

    @Operation(description = "Return list of school and their grades for selection in front-end", summary = "Retrieves schools and grades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schools and grades retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request while retrieving Schools and grades"),
            @ApiResponse(responseCode = "404", description = "Schools and grades not found"),
            @ApiResponse(responseCode = "500", description = "Unexpected error - Internal server error")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SchoolDTO>> listSchoolAndGrades() {
        return ResponseEntity.status(HttpStatus.OK).body(schoolService.findSchoolsAndGrades());
    }

}
