package ru.aryunin.FirstRestAPI.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SensorDTO {
    @NotEmpty
    @Size(min = 3, max = 30, message = "Name should be between 2 and 30 characters!")
    private String name;
}
