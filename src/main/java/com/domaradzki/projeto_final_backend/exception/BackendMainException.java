package com.domaradzki.projeto_final_backend.exception;

import java.text.MessageFormat;

public class BackendMainException extends Exception {

    public BackendMainException(final String message) {
        super(message);
    }

    public BackendMainException(final String message, Object... args) {
        super(MessageFormat.format(message, args));
    }

}
