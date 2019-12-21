package com.kodilla.carrentalbackend.mapper;

import com.kodilla.carrentalbackend.domain.User;
import com.kodilla.carrentalbackend.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser (final UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .userName(userDto.getUserName())
                .userSurname(userDto.getUserSurname())
                .mail(userDto.getMail())
                .phone(userDto.getPhone())
                .build();
    }

    public UserDto mapToUserDto ( final User user) {
        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .userSurname(user.getUserSurname())
                .mail(user.getMail())
                .phone(user.getPhone())
                .build();
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(u-> UserDto.builder()
                        .id(u.getId())
                        .userName(u.getUserName())
                        .userSurname(u.getUserSurname())
                        .mail(u.getMail())
                        .phone(u.getPhone())
                        .build())
                .collect(Collectors.toList());
    }

}
