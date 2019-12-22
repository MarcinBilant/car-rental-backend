package com.kodilla.carrentalbackend;

import com.kodilla.carrentalbackend.domain.GroupCar;
import com.kodilla.carrentalbackend.service.DbService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupCarTestSuite {
    @Autowired
    DbService service;

    @Test
    public void testGroupCarSave() {
        //Given
        GroupCar groupCar1 = new GroupCar(1L,"Mini","Samochody miejskie i na krótkie wycieczki.");
        //When
        GroupCar groupCar2 = service.saveGroupCar(groupCar1);

        //Then
        Long id = groupCar2.getId();
        Optional<GroupCar> readGroupCar = service.getGroupCar(id);
        Assert.assertTrue(readGroupCar.isPresent());

        //CleanUp
        service.deleteGroupCar(id);
    }

    @Test
    public void testGroupCarDelete() {
        //Given
        GroupCar groupCar1 = new GroupCar(1L,"Mini","Samochody miejskie i na krótkie wycieczki.");
        GroupCar groupCar2 = service.saveGroupCar(groupCar1);
        Long id = groupCar2.getId();

        //When
        service.deleteGroupCar(id);

        //Then

        Optional<GroupCar> readGroupCar = service.getGroupCar(id);
        Assert.assertFalse(readGroupCar.isPresent());

    }

    @Test
    public void TestGroupCarGet() {
        //Given
        GroupCar groupCar1 = new GroupCar(1L,"Mini","Samochody miejskie i na krótkie wycieczki.");
        GroupCar groupCar2 = service.saveGroupCar(groupCar1);
        Long id = groupCar2.getId();
        //When
        Optional<GroupCar> readGroupCar = service.getGroupCar(id);

        //Then
        Assert.assertEquals("Mini", readGroupCar.get().getName());

        //CleanUp
        service.deleteGroupCar(id);

    }

    @Test
    public void TestGroupCarGetAll() {
        //Given
        GroupCar groupCar1 = new GroupCar(1L,"Mini","Samochody miejskie i na krótkie wycieczki.");
        GroupCar groupCar2 = new GroupCar(2L,"Economy","Samochody nadają sie do jazdy po mieście");
        GroupCar groupCar3 = service.saveGroupCar(groupCar1);
        GroupCar groupCar4 = service.saveGroupCar(groupCar2);
        Long id3 = groupCar3.getId();
        Long id4 = groupCar4.getId();

        //When
        List<GroupCar> readGroupCars = service.getAllGroupCars();

        //Then
        Assert.assertEquals(6, readGroupCars.size());

        //CleanUp
        service.deleteGroupCar(id3);
        service.deleteGroupCar(id4);
    }

}
