package com.kodilla.carrentalbackend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservationDto {
    private Long id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Long userId;
    private Long carId;
    private BigDecimal toPay;

}
