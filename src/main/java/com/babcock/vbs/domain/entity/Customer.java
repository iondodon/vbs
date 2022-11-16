package com.babcock.vbs.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

import static javax.persistence.GenerationType.SEQUENCE;


@Setter
@Getter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "customer_id_generator")
    @SequenceGenerator(
        name = "customer_id_generator",
        sequenceName = "customer_id_generator",
        initialValue = 100
    )
    @Setter(AccessLevel.NONE)
    private Long id;

    @Type(type="uuid-char")
    @Column(nullable = false, unique = true, length = 36)
    private UUID uuid;

    @NotNull
    @NotBlank
    @Column(unique = true, nullable = false)
    private String username;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() ||
                id == null || ((Customer) o).getId() == null) {
            return false;
        }
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
