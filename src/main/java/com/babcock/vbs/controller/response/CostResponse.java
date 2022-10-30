package com.babcock.vbs.controller.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class CostResponse {
    @NotNull
    private final BigDecimal cost;
}
