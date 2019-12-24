package com.kodilla.carrentalbackend.Controller;

import com.google.gson.Gson;
import com.kodilla.carrentalbackend.controller.CarController;
import com.kodilla.carrentalbackend.domain.Car;
import com.kodilla.carrentalbackend.dto.CarDto;
import com.kodilla.carrentalbackend.mapper.CarMapper;
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
@WebMvcTest(CarController.class)

public class CarControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarMapper carMapper;

    @MockBean
    private DbService service;

    @Test
    public void shouldGetEmptyCars() throws Exception {
        //given
        List<Car> cars = new ArrayList<>();
        when(service.getAllCars()).thenReturn(cars);

        //when & then
        mockMvc.perform(get("/v1/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void shouldGetCars() throws Exception {
        //given
        List<Car> cars = new ArrayList<>();
        Car car1 = new Car(1L, "Toyota Aygo","manualna",3L,4L,
                true,new BigDecimal(15),null,null);
        Car car2 = new Car(2L, "Fiat 500","automatyczna",3L,4L,
                true,new BigDecimal(15),null,null);
        cars.add(car1);
        cars.add(car2);

        List<CarDto> carsDto = new ArrayList<>();
        CarDto carDto1 = new CarDto(1L, "Toyota Aygo","manualna",3L,4L,
                true,new BigDecimal(15),null);
        CarDto carDto2 = new CarDto(2L, "Fiat 500","automatyczna",3L,4L,
                true,new BigDecimal(15),null);
        carsDto.add(carDto1);
        carsDto.add(carDto2);

        when(service.getAllCars()).thenReturn(cars);
        when(carMapper.mapToCarDtoList(cars)).thenReturn(carsDto);

        //when&then
        mockMvc.perform(get("/v1/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].name",is("Toyota Aygo")))
                .andExpect(jsonPath("$[0].gearBox",is("manualna")))
                .andExpect(jsonPath("$[0].numberOfDoors",is(3)))
                .andExpect(jsonPath("$[0].numberOfPersons",is(4)))
                .andExpect(jsonPath("$[0].airConditioning",is(true)))
                .andExpect(jsonPath("$[0].pricePerDay",is(15)))
                .andExpect(jsonPath("$[1].id",is(2)))
                .andExpect(jsonPath("$[1].name",is("Fiat 500")))
                .andExpect(jsonPath("$[1].gearBox",is("automatyczna")))
                .andExpect(jsonPath("$[1].numberOfDoors",is(3)))
                .andExpect(jsonPath("$[1].numberOfPersons",is(4)))
                .andExpect(jsonPath("$[1].airConditioning",is(true)))
                .andExpect(jsonPath("$[1].pricePerDay",is(15)));

    }

    @Test
    public void shouldGetCar() throws Exception {
        //given
        Car car1 = new Car(1L, "Toyota Aygo","manualna",3L,4L,
                true,new BigDecimal(15),null,null);
        Optional<Car> car1Optional = Optional.of(car1);

        CarDto carDto1 = new CarDto(1L, "Toyota Aygo","manualna",3L,4L,
                true,new BigDecimal(15),null);

        when(service.getCar(1L)).thenReturn(car1Optional);
        when(carMapper.mapToCarDto(car1)).thenReturn(carDto1);

        //when&then
        mockMvc.perform(get("/v1/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name",is("Toyota Aygo")))
                .andExpect(jsonPath("$.gearBox",is("manualna")))
                .andExpect(jsonPath("$.numberOfDoors",is(3)))
                .andExpect(jsonPath("$.numberOfPersons",is(4)))
                .andExpect(jsonPath("$.airConditioning",is(true)))
                .andExpect(jsonPath("$.pricePerDay",is(15)));

    }

    @Test
    public void shouldDeleteCar() throws Exception {
        //given
        Car car1 = new Car(1L, "Toyota Aygo","manualna",3L,4L,
                true,new BigDecimal(15),null,null);

        //when&then
        mockMvc.perform(delete("/v1/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateCar() throws Exception {
        //given
        Car car1 = new Car(1L, "Toyota Aygo","manualna",3L,4L,
                true,new BigDecimal(15),null,null);

        CarDto carDto1 = new CarDto(1L, "Toyota Aygo","manualna",3L,4L,
                true,new BigDecimal(15),null);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto1);

        when(carMapper.mapToCar(carDto1)).thenReturn(car1);
        when(service.saveCar(car1)).thenReturn(car1);
        when(carMapper.mapToCarDto(car1)).thenReturn(carDto1);

        //when&then
        mockMvc.perform(put("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateCar() throws Exception {
        //given
        Car car1 = new Car(1L, "Toyota Aygo","manualna",3L,4L,
                true,new BigDecimal(15),null,null);

        CarDto carDto1 = new CarDto(1L, "Toyota Aygo","manualna",3L,4L,
                true,new BigDecimal(15),null);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto1);

        when(service.saveCar(car1)).thenReturn(car1);
        when(carMapper.mapToCar(carDto1)).thenReturn(car1);

        //when&then
        mockMvc.perform(post("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }

}
