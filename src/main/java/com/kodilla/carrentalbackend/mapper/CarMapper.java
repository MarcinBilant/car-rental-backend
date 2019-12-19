package com.kodilla.carrentalbackend.mapper;

import com.kodilla.carrentalbackend.domain.Car;
import com.kodilla.carrentalbackend.domain.Reservation;
import com.kodilla.carrentalbackend.dto.CarDto;
import com.kodilla.carrentalbackend.dto.ReservationDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {
    public Car mapToCar(final CarDto carDto) {
        return new Car(
                carDto.getId(),
                carDto.getName(),
                carDto.getGearBox(),
                carDto.getNumberOfDoors(),
                carDto.getNumberOfPersons(),
                carDto.getAirConditioning(),
                carDto.getPricePerDay(),
                null,
                null
        );
    }
    public CarDto mapToCarDto(final Car car) {
        return new CarDto(
                car.getId(),
                car.getName(),
                car.getGearBox(),
                car.getNumberOfDoors(),
                car.getNumberOfPersons(),
                car.getAirConditioning(),
                car.getPricePerDay(),
                car.getGroupId().getId()
        );
    }
    public List<CarDto> mapToCarDtoList(final List<Car> carList) {
        return carList.stream()
                .map(c-> new CarDto(c.getId(),c.getName(),c.getGearBox(),c.getNumberOfDoors(),
                        c.getNumberOfPersons(),c.getAirConditioning(),c.getPricePerDay(),c.getGroupId().getId()))
                .collect(Collectors.toList());
    }

    public List<Car> mapToCarList(final List<CarDto> carDtoList) {
        return carDtoList.stream()
                .map(c-> new Car(c.getId(),c.getName(),c.getGearBox(),c.getNumberOfDoors(),
                        c.getNumberOfPersons(),c.getAirConditioning(),c.getPricePerDay(),null,null))
                .collect(Collectors.toList());
    }

}
