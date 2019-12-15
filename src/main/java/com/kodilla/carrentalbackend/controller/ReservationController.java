package com.kodilla.carrentalbackend.controller;


import com.kodilla.carrentalbackend.dto.ReservationDto;
import com.kodilla.carrentalbackend.mapper.ReservationMapper;
import com.kodilla.carrentalbackend.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    }
}
