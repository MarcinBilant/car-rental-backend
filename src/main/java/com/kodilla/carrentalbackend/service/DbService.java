package com.kodilla.carrentalbackend.service;

import com.kodilla.carrentalbackend.domain.Car;
import com.kodilla.carrentalbackend.domain.GroupCar;
import com.kodilla.carrentalbackend.domain.Reservation;
import com.kodilla.carrentalbackend.domain.User;
import com.kodilla.carrentalbackend.repository.CarRepository;
import com.kodilla.carrentalbackend.repository.GroupCarRepository;
import com.kodilla.carrentalbackend.repository.ReservationRepository;
import com.kodilla.carrentalbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupCarRepository groupCarRepository;


    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    public void deleteReservation(final Long id) {
        reservationRepository.deleteById(id);
    }

    public Reservation saveReservation(final Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> getReservation(final Long id) {
        return reservationRepository.findById(id);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCar(final Long id) {
        return carRepository.findById(id);
    }

    public Car saveCar(final Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(final Long id) {
        carRepository.deleteById(id);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUser(final Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public GroupCar saveGroupCar(final GroupCar groupCar) {
        return groupCarRepository.save(groupCar);
    }

    public void deleteGroupCar(final Long id) {
        groupCarRepository.deleteById(id);
    }

    public Optional<GroupCar> getGroupCar(final Long id) {
        return groupCarRepository.findById(id);
    }

    public List<GroupCar> getAllGroupCars() {
        return groupCarRepository.findAll();
    }

}
