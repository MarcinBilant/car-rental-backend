package com.kodilla.carrentalbackend.repository;


import com.kodilla.carrentalbackend.domain.GroupCar;
import com.kodilla.carrentalbackend.domain.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GroupCarRepository extends CrudRepository<GroupCar, Long> {

    @Override
    List<GroupCar> findAll();

    @Override
    GroupCar save (GroupCar gropu);

    @Override
    void deleteById (Long id);

    @Override
    Optional<GroupCar> findById (Long id);
}
