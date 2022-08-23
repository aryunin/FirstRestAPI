package ru.aryunin.FirstRestAPI.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "Measurement")
@Getter
@Setter
@NoArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Min(value = -100, message = "The value should be greater than -100!")
    @Max(value = 100, message = "The value should be less than 100!")
    @Column(name = "value")
    private float value;
    @Column(name = "raining")
    private Boolean raining;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "\"when\"")
    private Date when;
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;
}
