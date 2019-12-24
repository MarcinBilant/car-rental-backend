package com.kodilla.carrentalbackend.controller;

import com.kodilla.carrentalbackend.dto.CarDto;
import com.kodilla.carrentalbackend.mapper.CarMapper;
import com.kodilla.carrentalbackend.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class CarController {
    @Autowired
    CarMapper carMapper;
    @Autowired
    DbService service;

    @RequestMapping(method = RequestMethod.POST, value = "/cars", consumes = APPLICATION_JSON_VALUE)
    public void createCar(@RequestBody CarDto carDto) {
        service.saveCar(carMapper.mapToCar(carDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cars", consumes = APPLICATION_JSON_VALUE)
    public CarDto updateCar(@RequestBody CarDto carDto) {
        return carMapper.mapToCarDto(service.saveCar(carMapper.mapToCar(carDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/cars/{carId}")
    public void deleteCar(@PathVariable Long carId) {
        service.deleteCar(carId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cars/{carId}")
    public CarDto getCar(@PathVariable Long carId) throws CarNotFoundException {
        return carMapper.mapToCarDto(service.getCar(carId).orElseThrow(CarNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cars")
    public List<CarDto> getCars() {
        return carMapper.mapToCarDtoList(service.getAllCars());
    }

}
