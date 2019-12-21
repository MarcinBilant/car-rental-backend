package com.kodilla.carrentalbackend.dto;

import com.kodilla.carrentalbackend.domain.Car;
import com.kodilla.carrentalbackend.domain.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
