package com.kodilla.carrentalbackend;

import com.kodilla.carrentalbackend.domain.Car;
import com.kodilla.carrentalbackend.dto.CarDto;
import com.kodilla.carrentalbackend.mapper.CarMapper;
import com.kodilla.carrentalbackend.service.DbService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarTestSuite {
    @Autowired
    private DbService service;
    @Autowired
    private CarMapper carMapper;

    @Test
    public void testCarSave() {
        //Given
        Car car1 = new Car(1L, "Toyota Aygo","manualna",3L,4L,
                true,new BigDecimal(15),null,null);
        //When
        Car car2 = service.saveCar(car1);

        //Then
        Long id = car2.getId();
        Optional<Car> readCar = service.getCar(id);
        Assert.assertTrue(readCar.isPresent());

        //CleanUp
        service.deleteCar(id);
    }

    @Test
    public void testCarDelete() {
        //Given
        Car car1 = new Car(1L, "Toyota Aygo","manualna",3L,4L,
                true,new BigDecimal(15),null,null);
        Car car2 = service.saveCar(car1);
        Long id = car2.getId();

        //When
        service.deleteCar(id);

        //Then

        Optional<Car> readCar = service.getCar(id);
        Assert.assertFalse(readCar.isPresent());

    }

    @Test
    public void TestCarGet() {
        //Given
        Car car1 = new Car(1L, "Toyota Aygo","manualna",3L,4L,
                true,new BigDecimal(15),null,null);
        Car car2 = service.saveCar(car1);
        Long id = car2.getId();
        //When
        Optional<Car> readCar = service.getCar(id);

        //Then
        Assert.assertEquals("Toyota Aygo", readCar.get().getName());

        //CleanUp
        service.deleteCar(id);

    }

    @Test
    public void TestCarGetAll() {
        //Given
        Car car1 = new Car(1L, "Toyota Aygo","manualna",3L,4L,
                true,new BigDecimal(15),null,null);
        Car car2 = new Car(2L, "Fiat 500","automatyczna",3L,4L,
                true,new BigDecimal(15),null,null);

        Car car3 = service.saveCar(car1);
        Car car4 = service.saveCar(car2);
        Long id3 = car3.getId();
        Long id4 = car4.getId();

        //When
        List<Car> readCars = service.getAllCars();

        //Then
        Assert.assertEquals(13, readCars.size());

        //CleanUp
        service.deleteCar(id3);
        service.deleteCar(id4);
    }
    @Test
    public void testCarDtoMap() {
        //Given
        Car car1 = Car.builder()
                .id(1L)
                .name("Toyota Aygo")
                .gearBox("manualna")
                .numberOfDoors(3L)
                .numberOfPersons(4L)
                .airConditioning(true)
                .pricePerDay(new BigDecimal(15))
                .groupId(null)
                .reservations(null)
                .build();

        //When
        Car car2 = service.saveCar(car1);

        //Then
        Long id = car2.getId();
        CarDto carDto = carMapper.mapToCarDto(car2);
        carDto.setName("Toyota Yaris");
        Assert.assertEquals("Toyota Yaris",carDto.getName());

        //CleanUp
        service.deleteCar(id);
    }
}
