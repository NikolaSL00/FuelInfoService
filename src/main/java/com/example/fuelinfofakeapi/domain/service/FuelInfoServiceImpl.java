package com.example.fuelinfofakeapi.domain.service;

import com.example.fuelinfofakeapi.api.error.Error;
import com.example.fuelinfofakeapi.api.error.ServiceNotAvailableError;
import com.example.fuelinfofakeapi.api.model.FuelInfoRequest;
import com.example.fuelinfofakeapi.api.model.FuelInfoResponse;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class FuelInfoServiceImpl implements FuelInfoService {

    public final static Double airlinePricePerKm = 18.8d;
    public final static Double railwayPricePerKm = 0.6d;
    public final static Double busPricePerKm = 0.28d;
    public final static Double carPricePerKm = 0.59d;

    @Override
    public Either<Error, FuelInfoResponse> getFuelInfo(FuelInfoRequest fuelInfoRequest) {
        return Try.of(() -> {
                    return FuelInfoResponse
                            .builder()
                            .from(fuelInfoRequest.getFrom())
                            .to(fuelInfoRequest.getTo())
                            .airlinePrice(fuelInfoRequest.getAirDistance() * (airlinePricePerKm / 1000))
                            .busPrice(fuelInfoRequest.getHighwayDistance() * (busPricePerKm / 1000))
                            .carPrice(fuelInfoRequest.getHighwayDistance() * (carPricePerKm / 1000))
                            .railwayPrice(fuelInfoRequest.getRailwayDistance() * (railwayPricePerKm / 1000))
                            .build();
                }).toEither()
                .mapLeft(throwable -> {
                    return new ServiceNotAvailableError();
                });
    }

}
