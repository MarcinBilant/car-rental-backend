package com.kodilla.carrentalbackend.Controller;

import com.google.gson.Gson;
import com.kodilla.carrentalbackend.controller.GroupCarController;
import com.kodilla.carrentalbackend.domain.GroupCar;
import com.kodilla.carrentalbackend.dto.GroupCarDto;
import com.kodilla.carrentalbackend.mapper.GroupCarMapper;
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
@WebMvcTest(GroupCarController.class)
public class GroupCarControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupCarMapper groupCarMapper;

    @MockBean
    private DbService service;

    @Test
    public void shouldGetEmptyGroupCars() throws Exception {
        //given
        List<GroupCar> groupCars = new ArrayList<>();
        when(service.getAllGroupCars()).thenReturn(groupCars);

        //when & then
        mockMvc.perform(get("/v1/groups").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void shouldGetGroupCars() throws Exception {
        //given
        List<GroupCar> groupCars = new ArrayList<>();
        GroupCar groupCar1 = new GroupCar(1L,"Mini","Samochody miejskie i na krótkie wycieczki.");
        GroupCar gropuCar2 = new GroupCar(2L,"Economy","Samochody nadają sie do jazdy po mieście");
        groupCars.add(groupCar1);
        groupCars.add(gropuCar2);

        List<GroupCarDto> groupCarsDto = new ArrayList<>();
        GroupCarDto groupCarDto1 = new GroupCarDto(1L,"Mini","Samochody miejskie i na krótkie wycieczki.");
        GroupCarDto groupCarDto2 = new GroupCarDto(2L,"Economy","Samochody nadają sie do jazdy po mieście");
        groupCarsDto.add(groupCarDto1);
        groupCarsDto.add(groupCarDto2);

        when(service.getAllGroupCars()).thenReturn(groupCars);
        when(groupCarMapper.mapToGroupCarDtoList(groupCars)).thenReturn(groupCarsDto);

        //when&then
        mockMvc.perform(get("/v1/groups").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].name",is("Mini")))
                .andExpect(jsonPath("$[0].description",is("Samochody miejskie i na krótkie wycieczki.")))
                .andExpect(jsonPath("$[1].id",is(2)))
                .andExpect(jsonPath("$[1].name",is("Economy")))
                .andExpect(jsonPath("$[1].description",is("Samochody nadają sie do jazdy po mieście")));

    }

    @Test
    public void shouldGetGroupCar() throws Exception {
        //given
        GroupCar groupCar1 = new GroupCar(1L,"Mini","Samochody miejskie i na krótkie wycieczki.");
        Optional<GroupCar> groupCar1Optional = Optional.of(groupCar1);

        GroupCarDto groupCarDto1 = new GroupCarDto(1L,"Mini","Samochody miejskie i na krótkie wycieczki.");

        when(service.getGroupCar(1L)).thenReturn(groupCar1Optional);
        when(groupCarMapper.mapToGroupCarDto(groupCar1)).thenReturn(groupCarDto1);

        //when&then
        mockMvc.perform(get("/v1/groups/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name",is("Mini")))
                .andExpect(jsonPath("$.description",is("Samochody miejskie i na krótkie wycieczki.")));
    }

    @Test
    public void shouldDeleteGroupCar() throws Exception {
        //given
        GroupCar groupCar1 = new GroupCar(1L,"Mini","Samochody miejskie i na krótkie wycieczki.");

        //when&then
        mockMvc.perform(delete("/v1/groups/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateGroupCar() throws Exception {
        //given
        GroupCar groupCar1 = new GroupCar(1L,"Mini","Samochody miejskie i na krótkie wycieczki.");
        GroupCarDto groupCarDto1 = new GroupCarDto(1L,"Mini","Samochody miejskie i na krótkie wycieczki.");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(groupCarDto1);

        when(groupCarMapper.mapToGroupCar(groupCarDto1)).thenReturn(groupCar1);
        when(service.saveGroupCar(groupCar1)).thenReturn(groupCar1);
        when(groupCarMapper.mapToGroupCarDto(groupCar1)).thenReturn(groupCarDto1);

        //when&then
        mockMvc.perform(put("/v1/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateGroupCar() throws Exception {
        //given
        GroupCar groupCar1 = new GroupCar(1L,"Mini","Samochody miejskie i na krótkie wycieczki.");
        GroupCarDto groupCarDto1 = new GroupCarDto(1L,"Mini","Samochody miejskie i na krótkie wycieczki.");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(groupCarDto1);

        when(service.saveGroupCar(groupCar1)).thenReturn(groupCar1);
        when(groupCarMapper.mapToGroupCar(groupCarDto1)).thenReturn(groupCar1);

        //when&then
        mockMvc.perform(post("/v1/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }
}
