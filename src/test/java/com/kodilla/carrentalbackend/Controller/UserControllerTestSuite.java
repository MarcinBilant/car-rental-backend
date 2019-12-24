package com.kodilla.carrentalbackend.Controller;

import com.google.gson.Gson;
import com.kodilla.carrentalbackend.controller.UserController;
import com.kodilla.carrentalbackend.domain.User;
import com.kodilla.carrentalbackend.dto.UserDto;
import com.kodilla.carrentalbackend.mapper.UserMapper;
import com.kodilla.carrentalbackend.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private DbService service;

    @Test
    public void shouldGetEmptyUsers() throws Exception {
        //given
        List<User> users = new ArrayList<>();
        when(service.getAllUsers()).thenReturn(users);

        //when & then
        mockMvc.perform(get("/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void shouldGetUsers() throws Exception {
        //given
        List<User> users = new ArrayList<>();
        User user1 = new User(1L,"Krzysiek","Kowalski","krzysiek@kowalski.com",
                "456989321",null);
        User user2 = new User(2L,"Jan","Malinowski","jan@malinowski.com",
                "456984561",null);
        users.add(user1);
        users.add(user2);

        List<UserDto> usersDto = new ArrayList<>();
        UserDto userDto1 = new UserDto(1L,"Krzysiek","Kowalski","krzysiek@kowalski.com",
                "456989321");
        UserDto userDto2 = new UserDto(2L,"Jan","Malinowski","jan@malinowski.com",
                "456984561");
        usersDto.add(userDto1);
        usersDto.add(userDto2);

        when(service.getAllUsers()).thenReturn(users);
        when(userMapper.mapToUserDtoList(users)).thenReturn(usersDto);

        //when&then
        mockMvc.perform(get("/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].userName",is("Krzysiek")))
                .andExpect(jsonPath("$[0].userSurname",is("Kowalski")))
                .andExpect(jsonPath("$[0].mail",is("krzysiek@kowalski.com")))
                .andExpect(jsonPath("$[0].phone",is("456989321")))
                .andExpect(jsonPath("$[1].id",is(2)))
                .andExpect(jsonPath("$[1].userName",is("Jan")))
                .andExpect(jsonPath("$[1].userSurname",is("Malinowski")))
                .andExpect(jsonPath("$[1].mail",is("jan@malinowski.com")))
                .andExpect(jsonPath("$[1].phone",is("456984561")));

    }

    @Test
    public void shouldGetUser() throws Exception {
        //given
        User user1 = new User(1L,"Krzysiek","Kowalski","krzysiek@kowalski.com",
                "456989321",null);
        Optional<User> user1Optional = Optional.of(user1);

        UserDto userDto1 = new UserDto(1L,"Krzysiek","Kowalski","krzysiek@kowalski.com",
                "456989321");

        when(service.getUser(1L)).thenReturn(user1Optional);
        when(userMapper.mapToUserDto(user1)).thenReturn(userDto1);

        //when&then
        mockMvc.perform(get("/v1/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.userName",is("Krzysiek")))
                .andExpect(jsonPath("$.userSurname",is("Kowalski")))
                .andExpect(jsonPath("$.mail",is("krzysiek@kowalski.com")))
                .andExpect(jsonPath("$.phone",is("456989321")));

    }

    @Test
    public void shouldDeleteUser() throws Exception {
        //given
        User user1 = new User(1L,"Krzysiek","Kowalski","krzysiek@kowalski.com",
                "456989321",null);

        //when&then
        mockMvc.perform(delete("/v1/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateUser() throws Exception {
        //given
        User user1 = new User(1L,"Krzysiek","Kowalski","krzysiek@kowalski.com",
                "456989321",null);
        UserDto userDto1 = new UserDto(1L,"Krzysiek","Kowalski","krzysiek@kowalski.com",
                "456989321");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto1);

        when(userMapper.mapToUser(userDto1)).thenReturn(user1);
        when(service.saveUser(user1)).thenReturn(user1);
        when(userMapper.mapToUserDto(user1)).thenReturn(userDto1);

        //when&then
        mockMvc.perform(put("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateUser() throws Exception {
        //given
        User user1 = new User(1L,"Krzysiek","Kowalski","krzysiek@kowalski.com",
                "456989321",null);
        UserDto userDto1 = new UserDto(1L,"Krzysiek","Kowalski","krzysiek@kowalski.com",
                "456989321");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto1);

        when(service.saveUser(user1)).thenReturn(user1);
        when(userMapper.mapToUser(userDto1)).thenReturn(user1);

        //when&then
        mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }


}
