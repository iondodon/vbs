package com.babcock.vbs.integration.database.repository;

import com.babcock.vbs.domain.entity.BookingDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Set;

public interface BookingDateRepository extends JpaRepository<BookingDate, Long> {
    @Query(
        "select bd \n" +
        "from BookingDate bd \n" +
        "where bd.bdate>=:from and bd.bdate<=:to"
    )
    Set<BookingDate> findAllInPeriodInclusive(LocalDate from, LocalDate to);
}
