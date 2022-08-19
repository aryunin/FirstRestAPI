package ru.aryunin.FirstRestAPI.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.aryunin.FirstRestAPI.models.Sensor;
import ru.aryunin.FirstRestAPI.services.SensorsService;

public class SensorValidator implements Validator {
    private final SensorsService sensorsService;

    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Sensor.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sensor sensor = (Sensor) o;

        if(sensorsService.findByName(sensor.getName()).isPresent()) errors.rejectValue("name", "", "A sensor with the same name already exists!");
    }
}
