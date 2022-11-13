package com.lionani07.helpdesk.handler;

import com.lionani07.helpdesk.exceptions.ResourceAlreadyExistException;
import com.lionani07.helpdesk.exceptions.ResourceNotFoundException;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@ControllerAdvice
public class HelpDeskExceptionHandler {

    private static final String MSG_NOT_FOUND = "Resource not found";
    private static final String MSG_BAD_REQUEST = "Bad request";

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadRequestError> handler(MethodArgumentNotValidException e, HttpServletRequest request) {

        val fieldMessageErrors = e.getFieldErrors().stream().map(FieldMessageError::new).collect(Collectors.toList());

        final BadRequestError badRequestError = new BadRequestError(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                MSG_BAD_REQUEST,
                "Invalid fields",
                System.currentTimeMillis()
        );

        badRequestError.setFieldErrors(fieldMessageErrors);

        return ResponseEntity.badRequest().body(badRequestError);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<StandardError> handler(ResourceAlreadyExistException e, HttpServletRequest request) {

        final StandardError standardError = StandardError.builder()
                .path(request.getRequestURI())
                .error(MSG_BAD_REQUEST)
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.badRequest().body(standardError);
    }
}
