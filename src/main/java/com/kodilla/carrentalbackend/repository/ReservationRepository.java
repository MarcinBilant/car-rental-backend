package com.kodilla.carrentalbackend.repository;

import com.kodilla.carrentalbackend.domain.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Override
    List<Reservation> findAll();

    @Override
    Reservation save(Reservation reservation);

    @Override
    void deleteById (Long id);

    @Override
    Optional<Reservation> findById (Long id);
}
