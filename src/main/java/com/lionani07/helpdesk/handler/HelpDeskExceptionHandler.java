package com.lionani07.helpdesk.handler;

import com.lionani07.helpdesk.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HelpDeskExceptionHandler {

    private static final String MSG_NOT_FOUND = "Resource not found";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> handler(ResourceNotFoundException e, HttpServletRequest request) {
        final var status = HttpStatus.NOT_FOUND;

        final StandardError standardError = StandardError.builder()
                .path(request.getRequestURI())
                .error(MSG_NOT_FOUND)
                .message(e.getMessage())
                .status(status.value())
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.status(status).body(standardError);
    }
}
