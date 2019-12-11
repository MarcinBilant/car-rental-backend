package com.kodilla.carrentalbackend.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Group {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String description;

    @OneToMany(
            targetEntity = Car.class,
            mappedBy ="groupId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Car> cars = new ArrayList<>();

}
