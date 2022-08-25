package com.example.fuelinfofakeapi.api.controller;

import com.example.fuelinfofakeapi.api.error.Error;
import com.example.fuelinfofakeapi.api.model.FuelInfoRequest;
import com.example.fuelinfofakeapi.api.model.FuelInfoResponse;
import com.example.fuelinfofakeapi.domain.service.FuelInfoService;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FuelInfoController {

    private final FuelInfoService fuelInfoService;

    public FuelInfoController(FuelInfoService fuelInfoService) {
        this.fuelInfoService = fuelInfoService;
    }

    @PostMapping("/fuelInfo")
    ResponseEntity<?> getFuelInfo(@RequestBody FuelInfoRequest fuelInfoRequest){
        Either<Error, FuelInfoResponse> response = fuelInfoService.getFuelInfo(fuelInfoRequest);

        if(response.isLeft()){
            return ResponseEntity
                    .status(response.getLeft().getCode())
                    .body(response.getLeft().getMessage());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response.get());
    }

}
