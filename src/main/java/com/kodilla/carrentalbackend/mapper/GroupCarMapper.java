package com.kodilla.carrentalbackend.mapper;

import com.kodilla.carrentalbackend.domain.GroupCar;
import com.kodilla.carrentalbackend.dto.GroupCarDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupCarMapper {

    public GroupCar mapToGroupCar(final GroupCarDto groupCarDto) {
        return GroupCar.builder()
                .id(groupCarDto.getId())
                .name(groupCarDto.getName())
                .description(groupCarDto.getDescription())
                .build();
    }

    public GroupCarDto mapToGroupCarDto(final GroupCar groupCar) {
        return GroupCarDto.builder()
                .id(groupCar.getId())
                .name(groupCar.getName())
                .description(groupCar.getDescription())
                .build();
    }

    public List<GroupCarDto> mapToGroupCarDtoList(final List<GroupCar> groupCarList) {
        return groupCarList.stream()
                .map(g-> GroupCarDto.builder()
                .id(g.getId())
                .name(g.getName())
                .description(g.getDescription())
                .build())
                .collect(Collectors.toList());
    }
}
