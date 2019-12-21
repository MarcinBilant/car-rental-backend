package com.kodilla.carrentalbackend.controller;

import com.kodilla.carrentalbackend.dto.GroupCarDto;
import com.kodilla.carrentalbackend.mapper.GroupCarMapper;
import com.kodilla.carrentalbackend.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class GroupCarController {
    @Autowired
    private DbService service;
    @Autowired
    private GroupCarMapper groupCarMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/groups", consumes = APPLICATION_JSON_VALUE)
    public void createGroupCar(@RequestBody GroupCarDto groupCarDto) {
        service.saveGroupCar(groupCarMapper.mapToGroupCar(groupCarDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/groups")
    public GroupCarDto updateGroupCar(@RequestBody GroupCarDto groupCarDto) {
        return groupCarMapper.mapToGroupCarDto((service.saveGroupCar(groupCarMapper.mapToGroupCar(groupCarDto))));
    }

    @RequestMapping(method = RequestMethod.DELETE, value ="/groups/{groupId}" )
    public void deleteGroupCarId(@PathVariable Long groupId) {
        service.deleteGroupCar(groupId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/groups/{groupId}")
    public GroupCarDto getGroupCar(@PathVariable Long groupId) throws GroupCarNotFoundException {
        return groupCarMapper.mapToGroupCarDto(service.getGroupCar(groupId).orElseThrow(GroupCarNotFoundException::new));

    }

    @RequestMapping(method = RequestMethod.GET,value = "/groups")
    public List<GroupCarDto> getGroupCars() {
        return groupCarMapper.mapToGroupCarDtoList(service.getAllGroupCars());
    }
}
