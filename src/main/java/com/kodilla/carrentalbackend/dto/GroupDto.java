package com.kodilla.carrentalbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GroupDto {
    private Long id;
    private String name;
    private String description;
}
