package com.example.fuelinfofakeapi.api.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FuelInfoRequest {
    private String from;
    private String to;

    private Double highwayDistance;
    private Double railwayDistance;
    private Double airDistance;
}
