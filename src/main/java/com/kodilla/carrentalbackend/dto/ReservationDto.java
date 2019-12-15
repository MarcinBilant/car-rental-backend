package com.kodilla.carrentalbackend.dto;

import com.kodilla.carrentalbackend.domain.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDto {
    private Long id;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private String userName;
    private String userSurname;
    private String mail;
    private String phone;
    private Car car;
    private BigDecimal toPay;

}