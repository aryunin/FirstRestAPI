package ru.aryunin.FirstRestAPI.util;

public class SensorAlreadyExistsException extends RuntimeException {
    public SensorAlreadyExistsException(String message) {
        super(message);
    }
}
