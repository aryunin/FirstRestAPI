package ru.aryunin.FirstRestAPI.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aryunin.FirstRestAPI.models.Measurement;
import ru.aryunin.FirstRestAPI.models.Sensor;
import ru.aryunin.FirstRestAPI.repositories.MeasurementRepository;
import ru.aryunin.FirstRestAPI.util.SensorNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
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

    @Transactional
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    public List<Measurement> getAll() {
        return measurementRepository.findAllJoin();
    }

    public List<Measurement> getRaining(Boolean raining) {
        return measurementRepository.findByRaining(raining);
    }
}
