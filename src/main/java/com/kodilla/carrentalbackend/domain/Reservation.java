package com.kodilla.carrentalbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private LocalDate dateFrom;

    @NotNull
    private LocalDate dateTo;

    @NotNull
    private String userName;

    @NotNull
    private String  userSurname;

    @NotNull
    private String mail;

    @NotNull
    private String phone;

    @NotNull
    @OneToOne
    @JoinColumn(name = "Car_Id")
    private Car carId;

    @NotNull
    private BigDecimal toPay;



}
