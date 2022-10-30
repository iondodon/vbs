package com.babcock.vbs.dto;

import com.babcock.vbs.business.validation.BookableDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class DatePeriodDto {
    @NotNull
    @BookableDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate from;

    @NotNull
    @BookableDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate to;
}
