package com.babcock.vbs.controller.advice;

import com.babcock.vbs.controller.response.ErrorResponse;
import com.babcock.vbs.exception.VbsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String METHOD_ARGUMENT_NOT_VALID_ERROR =
            "Method argument not valid error";
    private static final String CLIENT_ERROR = "Client error";
    public static final String ILLEGAL_ARGUMENT = "Illegal Argument";


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            ConstraintViolationException ex, HttpServletRequest request
    ) {
        ErrorResponse response = ErrorResponse.builder()
                .status(BAD_REQUEST)
                .error(CLIENT_ERROR)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBindException(
            MethodArgumentNotValidException ex, HttpServletRequest request
    ) {
        String message = ex.getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.toSet()).toString();

        ErrorResponse response = ErrorResponse.builder()
                .status(BAD_REQUEST)
                .error(METHOD_ARGUMENT_NOT_VALID_ERROR)
                .message(message)
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(VbsException.class)
    public ResponseEntity<ErrorResponse> handleVbsException(
            VbsException ex, HttpServletRequest request
    ) {
        ErrorResponse response = ErrorResponse.builder()
                .status(ex.getStatus())
                .error(ex.getStatus().getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleConversionFailure(
            IllegalArgumentException ex, HttpServletRequest request
    ) {
        ErrorResponse response = ErrorResponse.builder()
                .status(BAD_REQUEST)
                .error(ILLEGAL_ARGUMENT)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

}
