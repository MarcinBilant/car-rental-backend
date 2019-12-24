package com.kodilla.carrentalbackend.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private LocalDate dateFrom;

    @NotNull
    private LocalDate dateTo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CAR_ID")
    private Car car;

    @NotNull
    private BigDecimal toPay;



}
