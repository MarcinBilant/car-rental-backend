package com.kodilla.carrentalbackend.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Car {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Column(length = 20)
    private String gearBox;

    @NotNull
    private Long numberOfDoors;

    @NotNull
    private Long numberOfPersons;

    @NotNull
    private Boolean airConditioning;

    @NotNull
    private BigDecimal pricePerDay;


    @NotNull
    @ManyToOne
    @JoinColumn
    private Group group;



    @Builder
    public Car (Long id, String name, String gearBox, Long numberOfDoors, Long numberOfPersons, Boolean airConditioning,
                BigDecimal pricePerDay, Group group) {
        this.id = id;
        this.name = name;
        this.gearBox = gearBox;
        this.numberOfDoors = numberOfDoors;
        this.numberOfPersons = numberOfPersons;
        this.airConditioning = airConditioning;
        this.pricePerDay = pricePerDay;
        this.group = group;
    }



}
