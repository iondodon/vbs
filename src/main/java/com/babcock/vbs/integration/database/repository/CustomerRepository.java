package com.babcock.vbs.integration.database.repository;

import com.babcock.vbs.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUuid(UUID customerUuid);
}
