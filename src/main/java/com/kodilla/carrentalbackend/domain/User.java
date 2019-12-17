package com.kodilla.carrentalbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String userName;

    @NotNull
    private String userSurname;

    @NotNull
    private String mail;

    @NotNull
    private String phone;


}
