package ru.aryunin.FirstRestAPI.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aryunin.FirstRestAPI.models.Measurement;
import ru.aryunin.FirstRestAPI.models.Sensor;
import ru.aryunin.FirstRestAPI.repositories.MeasurementRepository;
import ru.aryunin.FirstRestAPI.util.SensorNotFoundException;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MeasurementsService {
    private final MeasurementRepository measurementRepository;
    private final SensorsService sensorsService;

    private void enrichMeasurement(Measurement measurement) {
        Optional<Sensor> sensor = sensorsService.findByName(measurement.getSensor().getName());
        if(sensor.isEmpty())
            throw new SensorNotFoundException();
        else measurement.setSensor(sensor.get());
        measurement.setWhen(new Date());
    }

    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }
}
