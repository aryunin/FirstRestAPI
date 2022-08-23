package ru.aryunin.FirstRestAPI.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class MeasurementDTO {
    @Min(value = -100, message = "The value should be greater than -100!")
    @Max(value = 100, message = "The value should be less than 100!")
    private float value;
    private Boolean raining;
    private SensorDTO sensor;
}
