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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @BookableDate(message = "Past starting date is not bookable")
    @NotNull(message = "Starting date is not specified")
    private final LocalDate fromDate;

    @BookableDate(message = "End date in the past is not bookable")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "End date is not specified")
    private final LocalDate toDate;
}
