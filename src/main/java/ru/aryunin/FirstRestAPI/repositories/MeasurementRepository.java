package ru.aryunin.FirstRestAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.aryunin.FirstRestAPI.models.Measurement;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    @Query(value = "SELECT m FROM Measurement m LEFT JOIN FETCH m.sensor s")
    List<Measurement> findAllJoin();
}
