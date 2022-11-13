package com.lionani07.helpdesk.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
@AllArgsConstructor
public class FieldMessageError {

    private String field;
    private String message;

    public FieldMessageError(final FieldError fieldError) {
        this.field = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
    }
}
