package ru.aryunin.FirstRestAPI.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aryunin.FirstRestAPI.models.Sensor;
import ru.aryunin.FirstRestAPI.repositories.SensorRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SensorsService {
    private final SensorRepository sensorRepository;

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
