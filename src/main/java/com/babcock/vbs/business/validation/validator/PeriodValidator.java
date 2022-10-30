package com.babcock.vbs.business.validation.validator;

import com.babcock.vbs.business.validation.ValidPeriod;
import com.babcock.vbs.dto.DatePeriodDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeriodValidator implements ConstraintValidator<ValidPeriod, DatePeriodDto> {
    @Override
    public boolean isValid(DatePeriodDto period, ConstraintValidatorContext context) {
        return period.getTo().isEqual(period.getFrom()) ||
                period.getTo().isAfter(period.getFrom());
    }
}
