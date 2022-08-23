package ru.aryunin.FirstRestAPI.util;

public class MeasurementNotAddedException extends RuntimeException {
    public MeasurementNotAddedException(String message) {
        super(message);
    }
}
