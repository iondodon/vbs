package com.babcock.vbs.domain.entities;

import com.babcock.vbs.domain.entities.enumerations.VehicleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Setter
@Getter
@Entity
@Table(name = "vehicle_category")
public class VehicleCategory {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "category_id_generator")
    @SequenceGenerator(name = "category_id_generator", sequenceName = "category_id_generator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 10)
    private VehicleType category;

    @NotNull
    @Positive
    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal pricePerDay;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() ||
                id == null || ((VehicleCategory) o).getId() == null) {
            return false;
        }
        VehicleCategory category = (VehicleCategory) o;
        return id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}