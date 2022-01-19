package com.dashboard.model.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SolarDataTotalResponse {
    private double totalWh;
    public SolarDataTotalResponse(double totalWh){
        this.totalWh = totalWh;
    }
}
