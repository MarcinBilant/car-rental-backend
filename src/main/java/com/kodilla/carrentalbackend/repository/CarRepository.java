package com.kodilla.carrentalbackend.repository;

import com.kodilla.carrentalbackend.domain.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
