package com.kodilla.carrentalbackend.dto;

import lombok.*;


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
