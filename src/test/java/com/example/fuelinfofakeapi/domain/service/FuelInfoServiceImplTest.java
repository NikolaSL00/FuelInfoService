package com.example.fuelinfofakeapi.domain.service;

import com.example.fuelinfofakeapi.api.error.Error;
import com.example.fuelinfofakeapi.api.model.FuelInfoRequest;
import com.example.fuelinfofakeapi.api.model.FuelInfoResponse;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FuelInfoServiceImplTest {

    private FuelInfoService toTest;

    @BeforeEach
    void setUp() {
        toTest = new FuelInfoServiceImpl();
    }

    @Test
    void returnDataShouldBeRelevantToRequestData() {
        // arrange
        FuelInfoRequest testReq = FuelInfoRequest.builder()
                .to("Varna")
                .from("Sofia")
                .airDistance(350d)
                .highwayDistance(550d)
                .railwayDistance(500d)
                .build();

        FuelInfoResponse testRes = FuelInfoResponse
                .builder()
                .to(testReq.getTo())
                .from(testReq.getFrom())
                .airlinePrice(testReq.getAirDistance() * (FuelInfoServiceImpl.airlinePricePerKm / 1000))
                .railwayPrice(testReq.getRailwayDistance() * (FuelInfoServiceImpl.railwayPricePerKm / 1000))
                .busPrice(testReq.getHighwayDistance() * (FuelInfoServiceImpl.busPricePerKm / 1000))
                .carPrice(testReq.getHighwayDistance() * (FuelInfoServiceImpl.carPricePerKm / 1000))
                .build();

        // act
        Either<Error, FuelInfoResponse> fuelInfoRes = toTest.getFuelInfo(testReq);

        // assert
        assertEquals(
                testRes.getFrom(),
                fuelInfoRes.get().getFrom());

        assertEquals(
                testRes.getTo(),
                fuelInfoRes.get().getTo());

        assertEquals(
                testRes.getAirlinePrice(),
                fuelInfoRes.get().getAirlinePrice());

        assertEquals(
                testRes.getRailwayPrice(),
                fuelInfoRes.get().getRailwayPrice());

        assertEquals(
                testRes.getBusPrice(),
                fuelInfoRes.get().getBusPrice());

        assertEquals(
                testRes.getCarPrice(),
                fuelInfoRes.get().getCarPrice());
    }


}