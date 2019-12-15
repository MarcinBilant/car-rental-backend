package com.kodilla.carrentalbackend.mapper;

import com.kodilla.carrentalbackend.domain.Car;
import com.kodilla.carrentalbackend.dto.CarDto;
import org.springframework.stereotype.Component;

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
                carDto.getGroupId()
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
                car.getGroupId()
        );
    }
}
