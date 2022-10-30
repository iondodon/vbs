package com.babcock.vbs.controller.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@Builder
public class ErrorResponse {
    private final Instant timestamp = Instant.now();
    private final HttpStatus status;
    private final String error;
    private final String message;
    private final String path;
}
