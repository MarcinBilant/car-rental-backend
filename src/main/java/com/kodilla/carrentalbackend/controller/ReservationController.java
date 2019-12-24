package com.kodilla.carrentalbackend.controller;

import com.kodilla.carrentalbackend.dto.ReservationDto;
import com.kodilla.carrentalbackend.mapper.ReservationMapper;
import com.kodilla.carrentalbackend.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class ReservationController {
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private DbService service;

    @RequestMapping(method = RequestMethod.POST, value = "/reservations", consumes = APPLICATION_JSON_VALUE)
    public void createReservations(@RequestBody ReservationDto reservationDto) {
        service.saveReservation(reservationMapper.mapToReservation(reservationDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/reservations", consumes = APPLICATION_JSON_VALUE)
    public ReservationDto updateReservations(@RequestBody ReservationDto reservationDto) {
        return reservationMapper.mapToReservationDto(service.saveReservation(reservationMapper.mapToReservation(reservationDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/reservations/{reservationId}")
    public void deleteReservation(@PathVariable Long reservationId) {
        service.deleteReservation(reservationId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reservations/{reservationId}")
    public ReservationDto getReservation(@PathVariable Long reservationId) throws ReservationNotFoundException {
        return reservationMapper.mapToReservationDto(service.getReservation(reservationId).orElseThrow(ReservationNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reservations")
    public List<ReservationDto> getReservations() {
        return reservationMapper.mapToReservationDtoList(service.getAllReservation());

    }
}

