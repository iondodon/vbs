package com.babcock.vbs.controller.advice;

import com.babcock.vbs.controller.response.ErrorResponse;
import com.babcock.vbs.exception.VbsException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.net.BindException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            ConstraintViolationException ex, HttpServletRequest request
    ) {
        ErrorResponse response = ErrorResponse.builder()
                .status(BAD_REQUEST)
                .error("Client error")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBindException(
            MethodArgumentNotValidException ex, HttpServletRequest request
    ) {
        String message = ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet()).toString();

        ErrorResponse response = ErrorResponse.builder()
                .status(BAD_REQUEST)
                .error("Client error")
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

}
