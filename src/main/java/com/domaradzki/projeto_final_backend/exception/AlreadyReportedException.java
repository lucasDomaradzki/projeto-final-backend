package com.domaradzki.projeto_final_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED)
public class AlreadyReportedException extends BackendMainException {

    public AlreadyReportedException(String message) {
        super(message);
    }

    public AlreadyReportedException(String message, Object... args) {
        super(message, args);
    }

}
