package com.kodilla.carrentalbackend.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.kodilla.carrentalbackend.controller.ReservationController;
import com.kodilla.carrentalbackend.domain.Reservation;
import com.kodilla.carrentalbackend.dto.ReservationDto;
import com.kodilla.carrentalbackend.mapper.ReservationMapper;
import com.kodilla.carrentalbackend.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTestSuite {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationMapper reservationMapper;

    @MockBean
    private DbService service;

    @Test
    public void shouldGetEmptyReservations() throws Exception {
        //given
        List<Reservation> reservations = new ArrayList<>();
        when(service.getAllReservation()).thenReturn(reservations);

        //when & then
        mockMvc.perform(get("/v1/reservations").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void shouldGetReservations() throws Exception {
        //given
        List<Reservation> reservations = new ArrayList<>();
        Reservation reservation1 = new Reservation(1L, LocalDate.of(2019,12,19),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(45));
        Reservation reservation2 = new Reservation(2L, LocalDate.of(2019,12,21),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(15));
        reservations.add(reservation1);
        reservations.add(reservation2);

        List<ReservationDto> reservationsDto = new ArrayList<>();
        ReservationDto reservationDto1 = new ReservationDto(1L, LocalDate.of(2019,12,19),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(45));
        ReservationDto reservationDto2 = new ReservationDto(2L, LocalDate.of(2019,12,21),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(15));
        reservationsDto.add(reservationDto1);
        reservationsDto.add(reservationDto2);

        when(service.getAllReservation()).thenReturn(reservations);
        when(reservationMapper.mapToReservationDtoList(reservations)).thenReturn(reservationsDto);

        //when&then
        mockMvc.perform(get("/v1/reservations").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].dateFrom",is("2019-12-19")))
                .andExpect(jsonPath("$[0].dateTo",is("2019-12-22")))
                .andExpect(jsonPath("$[0].toPay",is(45)))
                .andExpect(jsonPath("$[1].id",is(2)))
                .andExpect(jsonPath("$[1].dateFrom",is("2019-12-21")))
                .andExpect(jsonPath("$[1].dateTo",is("2019-12-22")))
                .andExpect(jsonPath("$[1].toPay",is(15)));
    }

    @Test
    public void shouldGetReservation() throws Exception {
        //given
        Reservation reservation1 = new Reservation(1L, LocalDate.of(2019,12,19),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(45));
        Optional<Reservation> reservation1Optional = Optional.of(reservation1);

        ReservationDto reservationDto1 = new ReservationDto(1L, LocalDate.of(2019,12,19),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(45));
        when(service.getReservation(1L)).thenReturn(reservation1Optional);
        when(reservationMapper.mapToReservationDto(reservation1)).thenReturn(reservationDto1);

        //when&then
        mockMvc.perform(get("/v1/reservations/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.dateFrom",is("2019-12-19")))
                .andExpect(jsonPath("$.dateTo",is("2019-12-22")))
                .andExpect(jsonPath("$.toPay",is(45)));

    }

    @Test
    public void shouldDeleteReservation() throws Exception {
        //given
        Reservation reservation1 = new Reservation(1L, LocalDate.of(2019,12,19),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(45));

        //when&then
        mockMvc.perform(delete("/v1/reservations/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateReservation() throws Exception {
        //given
        Reservation reservation1 = new Reservation(1L, LocalDate.of(2019,12,19),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(45));

        ReservationDto reservationDto1 = new ReservationDto(1L, LocalDate.of(2019,12,19),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(45));

        String jsonContent = objectMapper.writeValueAsString(reservationDto1);

        when(reservationMapper.mapToReservation(reservationDto1)).thenReturn(reservation1);
        when(service.saveReservation(reservation1)).thenReturn(reservation1);
        when(reservationMapper.mapToReservationDto(reservation1)).thenReturn(reservationDto1);

        //when&then
        mockMvc.perform(put("/v1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateReservation() throws Exception {
        //given
        Reservation reservation1 = new Reservation(1L, LocalDate.of(2019,12,19),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(45));

        ReservationDto reservationDto1 = new ReservationDto(1L, LocalDate.of(2019,12,19),
                LocalDate.of(2019,12,22), null,null,new BigDecimal(45));

        String jsonContent = objectMapper.writeValueAsString(reservationDto1);

        when(service.saveReservation(reservation1)).thenReturn(reservation1);
        when(reservationMapper.mapToReservation(reservationDto1)).thenReturn(reservation1);

        //when&then
        mockMvc.perform(post("/v1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

}
