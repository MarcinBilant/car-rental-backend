package com.kodilla.carrentalbackend.repository;


import com.kodilla.carrentalbackend.domain.GroupCar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface GroupCarRepository extends CrudRepository<GroupCar, Long> {

    @Override
    List<GroupCar> findAll();

    @Override
    GroupCar save (GroupCar groupCar);

    @Override
    void deleteById (Long id);

    @Override
    Optional<GroupCar> findById (Long id);
}
