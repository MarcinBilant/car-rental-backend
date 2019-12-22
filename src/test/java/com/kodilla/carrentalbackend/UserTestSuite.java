package com.kodilla.carrentalbackend;

import com.kodilla.carrentalbackend.domain.User;
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
public class UserTestSuite {
    @Autowired
    DbService service;

    @Test
    public void testUserSave() {
        //Given
        User user1 = new User(50L,"Krzysiek","Kowalski","krzysiek@kowalski.com",
                "456989321",null);
        //When
        User user2 = service.saveUser(user1);

        //Then
        Long id = user2.getId();
        Optional<User> readUser = service.getUser(id);
        Assert.assertTrue(readUser.isPresent());

        //CleanUp
        service.deleteUser(id);
    }

    @Test
    public void testUserDelete() {
        //Given
        User user1 = new User(50L,"Krzysiek","Kowalski","krzysiek@kowalski.com",
                "456989321",null);
        User user2 = service.saveUser(user1);
        Long id = user2.getId();

        //When
        service.deleteUser(id);

        //Then

        Optional<User> readUser = service.getUser(id);
        Assert.assertFalse(readUser.isPresent());

    }

    @Test
    public void TestUserGet() {
        //Given
        User user1 = new User(50L,"Krzysiek","Kowalski","krzysiek@kowalski.com",
                "456989321",null);
        User user2 = service.saveUser(user1);
        Long id = user2.getId();
        //When
        Optional<User> readUser = service.getUser(id);

        //Then
        Assert.assertEquals("Krzysiek", readUser.get().getUserName());

        //CleanUp
        service.deleteUser(id);

    }

    @Test
    public void TestUserGetAll() {
        //Given
        User user1 = new User(50L,"Krzysiek","Kowalski","krzysiek@kowalski.com",
                "456989321",null);
        User user2 = new User(51L,"Jan","Malinowski","jan@malinowski.com",
                "478989321",null);

        User user3 = service.saveUser(user1);
        User user4 = service.saveUser(user2);
        Long id3 = user3.getId();
        Long id4 = user4.getId();

        //When
        List<User> readUsers = service.getAllUsers();

        //Then
        Assert.assertEquals(2, readUsers.size());

        //CleanUp
        service.deleteUser(id3);
        service.deleteUser(id4);
    }

}
