package com.kodilla.carrentalbackend.mapper;

import com.kodilla.carrentalbackend.domain.Reservation;
import com.kodilla.carrentalbackend.dto.ReservationDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ReservationMapper {

    public Reservation mapToReservation(final ReservationDto reservationDto) {
        return new Reservation(
                reservationDto.getId(),
                reservationDto.getDateFrom(),
                reservationDto.getDateTo(),
                reservationDto.getUserName(),
                reservationDto.getUserSurname(),
                reservationDto.getMail(),
                reservationDto.getPhone(),
                null,
                reservationDto.getToPay()
        );
    }
    public ReservationDto mapToReservationDto(final Reservation reservation) {
        return new ReservationDto(
                reservation.getId(),
                reservation.getDateFrom(),
                reservation.getDateTo(),
                reservation.getUserName(),
                reservation.getUserSurname(),
                reservation.getMail(),
                reservation.getPhone(),
                reservation.getCar().getId(),
                reservation.getToPay()
        );
    }
    public List<ReservationDto> mapToReservationDtoList(final List<Reservation> reservationList) {
        return reservationList.stream()
                .map(r-> new ReservationDto(r.getId(),r.getDateFrom(),r.getDateTo(),r.getUserName(),
                        r.getUserSurname(),r.getMail(),r.getPhone(),r.getCar().getId(),r.getToPay()))
                .collect(Collectors.toList());
    }

    public List<Reservation> mapToReservationList(final List<ReservationDto> reservationDtoList) {
        return reservationDtoList.stream()
                .map(r-> new Reservation(r.getId(),r.getDateFrom(),r.getDateTo(),r.getUserName(),
                        r.getUserSurname(),r.getMail(),r.getPhone(),null,r.getToPay()))
                .collect(Collectors.toList());
    }
}
