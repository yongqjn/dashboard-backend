package com.dashboard.model.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolarCostSavedResponse {
    private double cost;

    public SolarCostSavedResponse(double cost){
        this.cost = cost;
    }
}
