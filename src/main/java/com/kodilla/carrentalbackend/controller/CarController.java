package com.kodilla.carrentalbackend.controller;

import com.kodilla.carrentalbackend.dto.CarDto;
import com.kodilla.carrentalbackend.mapper.CarMapper;
import com.kodilla.carrentalbackend.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class CarController {
    @Autowired
    CarMapper carMapper;
    @Autowired
    DbService service;

    @RequestMapping(method = RequestMethod.GET, value = "/cars")
    public List<CarDto> getCars() {
        return carMapper.mapToCarDtoList(service.getAllCars());

    }

    @RequestMapping(method = RequestMethod.GET, value = "/cars/{carId}")
    public CarDto getCar(@PathVariable Long carId) throws ReservationNotFoundException {
        return carMapper.mapToCarDto(service.getCar(carId).orElseThrow(ReservationNotFoundException::new));

    }

}
