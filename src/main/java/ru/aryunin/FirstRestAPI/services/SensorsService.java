package ru.aryunin.FirstRestAPI.services;

import org.springframework.stereotype.Service;
import ru.aryunin.FirstRestAPI.models.Sensor;
import ru.aryunin.FirstRestAPI.repositories.SensorRepository;

import java.util.Optional;

@Service
public class SensorsService {
    private final SensorRepository sensorRepository;

    public SensorsService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }
}
