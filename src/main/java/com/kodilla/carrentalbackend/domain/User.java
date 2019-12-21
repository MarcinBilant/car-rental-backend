package com.kodilla.carrentalbackend.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;


}
