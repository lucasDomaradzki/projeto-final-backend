package com.domaradzki.projeto_final_backend.config;

import com.domaradzki.projeto_final_backend.exception.AlreadyReportedException;
import com.domaradzki.projeto_final_backend.exception.BadRequestErrorException;
import com.domaradzki.projeto_final_backend.exception.InternalServerErrorException;
import com.domaradzki.projeto_final_backend.exception.NotFoundErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class BackendErrorExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {InternalServerErrorException.class})

    protected ResponseEntity<BackendDefaultResponse> handleInternalServerException(InternalServerErrorException exception, WebRequest request) {
        return ResponseEntity.internalServerError().body(new BackendDefaultResponse(INTERNAL_SERVER_ERROR.value(), exception.getMessage()));
    }

    @ExceptionHandler(value = {AlreadyReportedException.class})
    protected ResponseEntity<BackendDefaultResponse> handleAlreadyReportedException(AlreadyReportedException exception, WebRequest request) {
        return ResponseEntity.status(ALREADY_REPORTED).body(new BackendDefaultResponse(ALREADY_REPORTED.value(), exception.getMessage()));
    }

    @ExceptionHandler(value = {NotFoundErrorException.class})
    protected ResponseEntity<BackendDefaultResponse> handleNotFoundException(NotFoundErrorException exception, WebRequest request) {
        return ResponseEntity.status(NOT_FOUND).body(new BackendDefaultResponse(NOT_FOUND.value(), exception.getMessage()));
    }

    @ExceptionHandler(value = {BadRequestErrorException.class})
    protected ResponseEntity<BackendDefaultResponse> handleBadRequestException(BadRequestErrorException exception, WebRequest request) {
        return ResponseEntity.status(BAD_REQUEST).body(new BackendDefaultResponse(BAD_REQUEST.value(), exception.getMessage()));
    }

}
