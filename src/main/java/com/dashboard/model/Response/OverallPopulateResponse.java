package com.dashboard.model.Response;

import com.dashboard.model.Repository.OverallModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class OverallPopulateResponse {
    private double costSaved;
    private double totalGenerated;
    private String date;

    public OverallPopulateResponse(OverallModel model){
        costSaved = model.getCostSaved();
        totalGenerated = model.getTotalGenerated();
        date = model.getDate();
    }

}
