package com.babcock.vbs.integration.database.repository;

import com.babcock.vbs.domain.entity.BookingDate;
import com.babcock.vbs.domain.entity.Vehicle;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(
        "select v \n" +
        "from Vehicle v \n" +
            "left join fetch v.bookings b \n" +
            "left join fetch b.bookedDates bd \n" +
        "where bd.bdate <> :bdate"
    )
    @EntityGraph(
        type = EntityGraph.EntityGraphType.LOAD,
        attributePaths = "category"
    )
    List<Vehicle> getAvailableForHireOnDate(LocalDate bdate);

    @Override
    @EntityGraph(
            type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = "category"
    )
    List<Vehicle> findAll();

    @EntityGraph(
        type = EntityGraph.EntityGraphType.LOAD,
        attributePaths = "category"
    )
    Optional<Vehicle> findByUuid(UUID uuid);

    @Query(
        "select bd \n" +
        "from Vehicle v \n" +
            "join v.bookings b \n" +
            "join b.bookedDates bd \n" +
        "where v.uuid=:vehicleUuid and bd.bdate>=:from and bd.bdate<=:to"
    )
    List<BookingDate> findBookedDatesForVehicleByPeriod(UUID vehicleUuid, LocalDate from, LocalDate to);
}
