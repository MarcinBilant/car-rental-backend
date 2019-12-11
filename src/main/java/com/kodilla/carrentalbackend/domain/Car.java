package com.kodilla.carrentalbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
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
    private Boolean AirConditioning;

    @NotNull
    @ManyToOne
    @JoinColumn
    private Long groupId;

}
