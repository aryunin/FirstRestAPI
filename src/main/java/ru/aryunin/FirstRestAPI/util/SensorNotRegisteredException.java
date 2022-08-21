package ru.aryunin.FirstRestAPI.util;

public class SensorNotRegisteredException extends RuntimeException {
    public SensorNotRegisteredException(String message) {
        super(message);
    }
}
