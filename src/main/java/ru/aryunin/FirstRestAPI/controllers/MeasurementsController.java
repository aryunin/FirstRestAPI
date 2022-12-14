package ru.aryunin.FirstRestAPI.controllers;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.aryunin.FirstRestAPI.DTO.MeasurementDTO;
import ru.aryunin.FirstRestAPI.DTO.SensorDTO;
import ru.aryunin.FirstRestAPI.models.Measurement;
import ru.aryunin.FirstRestAPI.services.MeasurementsService;
import ru.aryunin.FirstRestAPI.util.ErrorResponse;
import ru.aryunin.FirstRestAPI.util.MeasurementNotAddedException;
import ru.aryunin.FirstRestAPI.util.SensorNotFoundException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/measurements")
@AllArgsConstructor
public class MeasurementsController {
    private final ModelMapper modelMapper;
    private final MeasurementsService measurementsService;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult) {
        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);

        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(value ->
                    errorMsg.append(value.getField())
                            .append(" - ")
                            .append(value.getDefaultMessage())
                            .append("; ")
            );

            throw new MeasurementNotAddedException(errorMsg.toString());
        }

        measurementsService.save(measurement);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public List<MeasurementDTO> getAll() {
        List<MeasurementDTO> measurements = new ArrayList<>();
        for(Measurement m : measurementsService.getAll())
            measurements.add(convertToDTO(m));
        return measurements;
    }

    @GetMapping("/rainyDaysCount")
    @ResponseBody
    public Integer getRainyDaysCount() {
        return measurementsService.getRaining(true).size();
    }

    private MeasurementDTO convertToDTO(Measurement m) {
        SensorDTO sensorDTO = modelMapper.map(m.getSensor(), SensorDTO.class);
        MeasurementDTO measurementDTO = modelMapper.map(m, MeasurementDTO.class);
        measurementDTO.setSensor(sensorDTO);
        return measurementDTO;
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotAddedException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                "A sensor with same name does not exist!",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
