package com.kodilla.carrentalbackend.dto;


import com.kodilla.carrentalbackend.domain.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDto {
    private Long id;
    private String name;
    private String gearBox;
    private Long numberOfDoors;
    private Long numberOfPersons;
    private Boolean AirConditioning;
    private BigDecimal pricePerDay;
    private Group group;

}
