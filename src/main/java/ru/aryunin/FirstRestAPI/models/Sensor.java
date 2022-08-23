package ru.aryunin.FirstRestAPI.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Sensor")
@Getter
@Setter
@NoArgsConstructor
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 3, max = 30, message = "Name should be between 2 and 30 characters!")
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurement;
}
