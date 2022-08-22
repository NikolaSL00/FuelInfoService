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

    private final Double airlinePricePerKm = 18.8d;
    private final Double railwayPricePerKm = 0.6d;
    private final Double busPricePerKm = 0.28d;
    private final Double carPricePerKm = 0.59d;


    @Override
    public Either<Error, FuelInfoResponse> getFuelInfo(FuelInfoRequest fuelInfoRequest) {
        return Try.of(() -> {
                    return FuelInfoResponse
                            .builder()
                            .from(fuelInfoRequest.getFrom())
                            .to(fuelInfoRequest.getTo())
                            .airlinePrice(fuelInfoRequest.getAirDistance() * (this.airlinePricePerKm / 1000))
                            .busPrice(fuelInfoRequest.getHighwayDistance() * (this.busPricePerKm / 1000))
                            .carPrice(fuelInfoRequest.getHighwayDistance() * (this.carPricePerKm / 1000))
                            .railwayPrice(fuelInfoRequest.getRailwayDistance() * (this.railwayPricePerKm / 1000))
                            .build();
                }).toEither()
                .mapLeft(throwable -> {
                    return new ServiceNotAvailableError();
                });
    }

}
