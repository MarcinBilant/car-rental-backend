package com.kodilla.carrentalbackend.dto;


import com.kodilla.carrentalbackend.domain.GroupCar;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarDto {
    private Long id;
    private String name;
    private String gearBox;
    private Long numberOfDoors;
    private Long numberOfPersons;
    private Boolean airConditioning;
    private BigDecimal pricePerDay;
    private Long groupId;

}
