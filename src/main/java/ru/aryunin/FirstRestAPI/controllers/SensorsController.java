package ru.aryunin.FirstRestAPI.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.aryunin.FirstRestAPI.DTO.SensorDTO;
import ru.aryunin.FirstRestAPI.models.Sensor;
import ru.aryunin.FirstRestAPI.services.SensorsService;
import ru.aryunin.FirstRestAPI.util.SensorNotRegisteredException;
import ru.aryunin.FirstRestAPI.util.ErrorResponse;
import ru.aryunin.FirstRestAPI.util.SensorValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensors")
@AllArgsConstructor
public class SensorsController {
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        Sensor sensor = modelMapper.map(sensorDTO, Sensor.class);

        sensorValidator.validate(sensor, bindingResult);
        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(value ->
                errorMsg.append(value.getField())
                        .append(" - ")
                        .append(value.getDefaultMessage())
                        .append("; ")
            );

            throw new SensorNotRegisteredException(errorMsg.toString());
        }

        sensorsService.save(sensor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotRegisteredException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
