package com.domaradzki.projeto_final_backend.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BackendDefaultResponse {

    public BackendDefaultResponse(int statusCode, String errorDescription) {
        this.httpStatus = statusCode;
        this.message = errorDescription;
        this.timestamp = LocalDateTime.now();
        this.createdId = null;
    }

    public BackendDefaultResponse(int statusCode, String errorDescription, Long createdId) {
        this.httpStatus = statusCode;
        this.message = errorDescription;
        this.timestamp = LocalDateTime.now();
        this.createdId = createdId;
    }

    private final int httpStatus;
    private final String message;
    private final LocalDateTime timestamp;
    private final Long createdId;


}
