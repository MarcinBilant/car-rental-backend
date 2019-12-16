package com.kodilla.carrentalbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private LocalDateTime dateFrom;

    @NotNull
    private LocalDateTime dateTo;

    @NotNull
    private String userName;

    @NotNull
    private String  userSurname;

    @NotNull
    private String mail;

    @NotNull
    private String phone;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="CAR_ID")
    private Car car;

    @NotNull
    private BigDecimal toPay;



}
