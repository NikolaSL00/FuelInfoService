package com.example.fuelinfofakeapi.api.error;

import org.springframework.http.HttpStatus;

public class ServiceNotAvailableError implements Error{
    @Override
    public HttpStatus getCode() {
        return HttpStatus.SERVICE_UNAVAILABLE;
    }

    @Override
    public String getMessage() {
        return "Service is not available.";
    }
}
