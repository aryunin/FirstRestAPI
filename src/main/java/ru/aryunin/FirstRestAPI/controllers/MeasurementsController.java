package ru.aryunin.FirstRestAPI.controllers;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.aryunin.FirstRestAPI.DTO.MeasurementDTO;
import ru.aryunin.FirstRestAPI.util.ErrorResponse;
import ru.aryunin.FirstRestAPI.util.MeasurementNotAddedException;

import javax.validation.Valid;

@RestController
@RequestMapping("/measurements")
@AllArgsConstructor
public class MeasurementsController {

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult) {
        // TODO
        return null;
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotAddedException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
