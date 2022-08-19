package ru.aryunin.FirstRestAPI.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Sensor")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "id")
    @NonNull private String name;
}
