package com.kodilla.carrentalbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CarDto {
    private Long id;
    private String name;
    private String gearBox;
    private Long numberOfDoors;
    private Long numberOfPersons;
    private Boolean AirConditioning;
    private Long groupId;

}
