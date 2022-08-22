package com.example.fuelinfofakeapi.domain.service;

import com.example.fuelinfofakeapi.api.error.Error;
import com.example.fuelinfofakeapi.api.model.FuelInfoRequest;
import com.example.fuelinfofakeapi.api.model.FuelInfoResponse;
import io.vavr.control.Either;

public interface FuelInfoService {
    Either<Error, FuelInfoResponse> getFuelInfo(FuelInfoRequest fuelInfoRequest);
}
