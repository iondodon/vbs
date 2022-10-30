package com.babcock.vbs.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public class VbsException extends RuntimeException {
    private final HttpStatus status;

    public VbsException(String message) {
        super(message);
        this.status = BAD_REQUEST;
    }

    public VbsException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
