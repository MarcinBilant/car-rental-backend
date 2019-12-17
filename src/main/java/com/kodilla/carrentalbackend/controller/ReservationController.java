package com.kodilla.carrentalbackend.controller;


import com.kodilla.carrentalbackend.domain.Reservation;
import com.kodilla.carrentalbackend.dto.ReservationDto;
import com.kodilla.carrentalbackend.mapper.ReservationMapper;
import com.kodilla.carrentalbackend.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class ReservationController {
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private DbService service;

    @RequestMapping(method = RequestMethod.GET, value = "/reservations")
    public List<ReservationDto> getReservations() {
        return reservationMapper.mapToReservationDtoList(service.getAllReservation());
       /* List<ReservationDto> reservations = new ArrayList<>();
        LocalDate dateFrom = LocalDate.of(2019, Month.SEPTEMBER, 1);
        LocalDate dateTo = dateFrom.plusDays(5);
        ReservationDto reservationDto = new ReservationDto(1l, dateFrom,dateTo,"Marcin","Kowalski","kowalski@kowalski.com",
                "6658789990",1,new BigDecimal(100));
        reservations.add(reservationDto);
        return reservations;*/

        }
    }
