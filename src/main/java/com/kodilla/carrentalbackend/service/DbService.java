package com.kodilla.carrentalbackend.service;

import com.kodilla.carrentalbackend.domain.Reservation;
import com.kodilla.carrentalbackend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    ReservationRepository reservationRepository;

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

}
