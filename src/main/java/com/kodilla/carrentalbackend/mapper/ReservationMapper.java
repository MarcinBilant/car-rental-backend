package com.kodilla.carrentalbackend.mapper;

import com.kodilla.carrentalbackend.domain.Reservation;
import com.kodilla.carrentalbackend.dto.ReservationDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ReservationMapper {

    public Reservation mapToReservation(final ReservationDto reservationDto) {
        return Reservation.builder()
                .id(reservationDto.getId())
                .dateFrom(reservationDto.getDateFrom())
                .dateTo(reservationDto.getDateTo())
                .toPay(reservationDto.getToPay())
                .build();
    }

    public ReservationDto mapToReservationDto(final Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .dateFrom(reservation.getDateFrom())
                .dateTo(reservation.getDateTo())
                .toPay(reservation.getToPay())
                .build();
    }

    public List<ReservationDto> mapToReservationDtoList(final List<Reservation> reservationList) {
        return reservationList.stream()
                .map(r-> ReservationDto.builder()
                .id(r.getId())
                .dateFrom(r.getDateFrom())
                .dateTo(r.getDateTo())
                .toPay(r.getToPay())
                .build())
                .collect(Collectors.toList());
    }
}
