package com.babcock.vbs.integration.database.repository;

import com.babcock.vbs.domain.entities.Vehicle;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


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
}
