package com.kodilla.carrentalbackend;

import com.kodilla.carrentalbackend.domain.Reservation;
import com.kodilla.carrentalbackend.dto.ReservationDto;
import com.kodilla.carrentalbackend.mapper.ReservationMapper;
import com.kodilla.carrentalbackend.service.DbService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationTestSuite {
    @Autowired
    private DbService service;
    @Autowired
    private ReservationMapper reservationMapper;

    @Test
    public void testReservationSave() {
        //Given
        Reservation reservation1 = new Reservation(1L, LocalDate.of(2019,12,19),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(45));
        //When
        Reservation reservation2 = service.saveReservation(reservation1);

        //Then
        Long id = reservation2.getId();
        Optional<Reservation> readReservation = service.getReservation(id);
        Assert.assertTrue(readReservation.isPresent());

        //CleanUp
        service.deleteReservation(id);
    }

    @Test
    public void testReservationDelete() {
        //Given
        Reservation reservation1 = new Reservation(1L, LocalDate.of(2019,12,19),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(45));
        Reservation reservation2 = service.saveReservation(reservation1);
        Long id = reservation2.getId();

        //When
        service.deleteReservation(id);

        //Then

        Optional<Reservation> readReservation = service.getReservation(id);
        Assert.assertFalse(readReservation.isPresent());

    }

    @Test
    public void TestReservationGet() {
        //Given
        Reservation reservation1 = new Reservation(1L, LocalDate.of(2019,12,19),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(45));
        Reservation reservation2 = service.saveReservation(reservation1);
        Long id = reservation2.getId();
        //When
        Optional<Reservation> readReservation = service.getReservation(id);

        //Then
        Assert.assertEquals(LocalDate.of(2019,12,19), readReservation.get().getDateFrom());

        //CleanUp
        service.deleteReservation(id);

    }

    @Test
    public void TestReservationGetAll() {
        //Given
        Reservation reservation1 = new Reservation(1L, LocalDate.of(2019,12,19),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(45));
        Reservation reservation2 = new Reservation(1L, LocalDate.of(2019,12,21),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(15));

        Reservation reservation3 = service.saveReservation(reservation1);
        Reservation reservation4 = service.saveReservation(reservation2);
        Long id3 = reservation3.getId();
        Long id4 = reservation4.getId();

        //When
        List<Reservation> readReservations = service.getAllReservation();

        //Then
        Assert.assertEquals(18, readReservations.size());

        //CleanUp
        service.deleteReservation(id3);
        service.deleteReservation(id4);
    }
    @Test
    public void testReservationDto() {
        //Given
        Reservation reservation1 = new Reservation(1L, LocalDate.of(2019,12,19),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(45));
        //When
        Reservation reservation2 = service.saveReservation(reservation1);

        //Then
        Long id = reservation2.getId();
        ReservationDto reservationDto = reservationMapper.mapToReservationDto(reservation2);
        reservationDto.setDateFrom(LocalDate.of(2019,12,15));
        Assert.assertEquals(LocalDate.of(2019,12,15),reservationDto.getDateFrom());

        //CleanUp
        service.deleteReservation(id);
    }
}
