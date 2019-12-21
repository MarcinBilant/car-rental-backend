package com.kodilla.carrentalbackend.dto;

import com.kodilla.carrentalbackend.domain.Car;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GroupCarDto {
    private Long id;
    private String name;
    private String description;
}
