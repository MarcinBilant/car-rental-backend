package com.kodilla.carrentalbackend.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @JoinColumn(name = "groupId")
    private GroupCar groupId;

    @OneToMany (mappedBy = "car")
    private List<Reservation> reservations;





    /*public Car (Long id, String name, String gearBox, Long numberOfDoors, Long numberOfPersons, Boolean airConditioning,
                BigDecimal pricePerDay, GroupCar groupId) {
        this.id = id;
        this.name = name;
        this.gearBox = gearBox;
        this.numberOfDoors = numberOfDoors;
        this.numberOfPersons = numberOfPersons;
        this.airConditioning = airConditioning;
        this.pricePerDay = pricePerDay;
        this.groupId = groupId;
    }*/
    /*Car car = Car.builder()
            .id(1l)
            .name("Fiat")
            .build();*/




}
