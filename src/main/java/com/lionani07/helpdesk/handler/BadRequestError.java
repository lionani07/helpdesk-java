package com.lionani07.helpdesk.handler;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BadRequestError extends StandardError {

    private List<FieldMessageError> fieldErrors = new ArrayList<>();

    public BadRequestError(String path, int status, String error, String message, Long timestamp) {
        super(path, status, error, message, timestamp);
    }

}
