package com.kodilla.carrentalbackend.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String userName;
    private String userSurname;
    private String mail;
    private String phone;
}
