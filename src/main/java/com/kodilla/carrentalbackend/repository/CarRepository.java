package com.kodilla.carrentalbackend.repository;

import com.kodilla.carrentalbackend.domain.Car;
import com.kodilla.carrentalbackend.domain.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {

    @Override
    List<Car> findAll();

    @Override
    Car save(Car car);

    @Override
    void deleteById (Long id);

    @Override
    Optional<Car> findById (Long id);
}
