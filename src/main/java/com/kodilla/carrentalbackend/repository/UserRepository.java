package com.kodilla.carrentalbackend.repository;

import com.kodilla.carrentalbackend.domain.Reservation;
import com.kodilla.carrentalbackend.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {


    @Override
    User save(User user);

    @Override
    void deleteById (Long id);

    @Override
    Optional<User> findById (Long id);

    @Override
    List<User> findAll();
}
