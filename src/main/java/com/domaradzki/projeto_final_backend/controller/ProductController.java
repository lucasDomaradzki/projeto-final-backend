package com.domaradzki.projeto_final_backend.controller;

import com.domaradzki.projeto_final_backend.exception.BackendMainException;
import com.domaradzki.projeto_final_backend.model.dto.ProductListDTO;
import com.domaradzki.projeto_final_backend.service.IProductListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/product")
@Tag(name = "Product related", description = "Product related APIs")
@AllArgsConstructor
public class ProductController {

    private IProductListService productListService;

    @Operation(description = "Return product list by school and grade IDs", summary = "Retrieves product list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product list retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request while retrieving Product list"),
        @ApiResponse(responseCode = "404", description = "Product list not found"),
        @ApiResponse(responseCode = "500", description = "Unexpected error - Internal server error")
    })
    @GetMapping(value = "/product/school/{schoolId}/grade/{gradeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductListDTO>> findProductListBySchoolAndGrade(@PathVariable(name = "schoolId") String schoolId, @PathVariable(name = "gradeId") String gradeId) throws BackendMainException {
        return ResponseEntity.status(HttpStatus.OK).body(productListService.findProductListsBySchoolUUIDAndGradeUUID(schoolId, gradeId));
    }

    @Operation(description = "Return product list details by product list id", summary = "Retrieves product list details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product list details retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request while retrieving Product list details "),
        @ApiResponse(responseCode = "404", description = "Product list details not found"),
        @ApiResponse(responseCode = "500", description = "Unexpected error - Internal server error")
    })
    @GetMapping(value = "/product/productlist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductListDTO> findProductListDetails(@PathVariable(name = "id") String id) throws BackendMainException {
        return ResponseEntity.status(HttpStatus.OK).body(productListService.findProductListDetailsByProductListUUID(id));
    }

}
