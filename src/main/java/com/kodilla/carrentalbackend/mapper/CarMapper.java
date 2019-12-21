package com.kodilla.carrentalbackend.mapper;

import com.kodilla.carrentalbackend.domain.Car;
import com.kodilla.carrentalbackend.domain.GroupCar;
import com.kodilla.carrentalbackend.dto.CarDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    public Car mapToCar(final CarDto carDto) {
        return Car.builder()
                .id(carDto.getId())
                .name(carDto.getName())
                .gearBox(carDto.getGearBox())
                .numberOfDoors(carDto.getNumberOfDoors())
                .numberOfPersons(carDto.getNumberOfPersons())
                .airConditioning(carDto.getAirConditioning())
                .pricePerDay(carDto.getPricePerDay())
                .build();
    }

    public CarDto mapToCarDto(final Car car) {
        return CarDto.builder()
                .id(car.getId())
                .name(car.getName())
                .gearBox(car.getGearBox())
                .numberOfDoors(car.getNumberOfDoors())
                .numberOfPersons(car.getNumberOfPersons())
                .airConditioning(car.getAirConditioning())
                .pricePerDay(car.getPricePerDay())
                .build();
    }



    public List<CarDto> mapToCarDtoList(final List<Car> carList) {
        return carList.stream()
                .map(c-> CarDto.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .gearBox(c.getGearBox())
                        .numberOfDoors(c.getNumberOfDoors())
                        .numberOfPersons(c.getNumberOfPersons())
                        .airConditioning(c.getAirConditioning())
                        .pricePerDay(c.getPricePerDay())
                        .build())
                .collect(Collectors.toList());
    }

}
