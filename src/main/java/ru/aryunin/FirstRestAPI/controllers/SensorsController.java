package ru.aryunin.FirstRestAPI.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.aryunin.FirstRestAPI.models.Sensor;
import ru.aryunin.FirstRestAPI.services.SensorsService;
import ru.aryunin.FirstRestAPI.util.SensorAlreadyExistsException;
import ru.aryunin.FirstRestAPI.util.SensorErrorResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensors")
@AllArgsConstructor
public class SensorsController {
    private final SensorsService sensorsService;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid Sensor sensor, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(value ->
                errorMsg.append(value.getField())
                        .append(" - ")
                        .append(value.getRejectedValue())
                        .append(";\n")
            );

            throw new SensorAlreadyExistsException(errorMsg.toString());
        }

        sensorsService.save(sensor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorAlreadyExistsException e) {
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
