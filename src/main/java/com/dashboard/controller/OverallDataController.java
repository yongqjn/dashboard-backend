package com.dashboard.controller;


import com.dashboard.model.Repository.OverallModel;
import com.dashboard.model.Request.SolarDataRequest;
import com.dashboard.model.Response.OverallDayGeneratedResponse;
import com.dashboard.model.Response.OverallDaySavedResponse;
import com.dashboard.model.Response.OverallPopulateResponse;
import com.dashboard.services.SolarDataService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/overall")
public class OverallDataController {

    @Autowired
    SolarDataService solarDataService;

    @PostMapping("/populate")
    public ResponseEntity<?> populateOverallTable(@RequestBody SolarDataRequest request){
        try{
            OverallPopulateResponse response = solarDataService.populateOverallTable(request);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getOverallValues(){
        try {
            List<OverallModel> list = solarDataService.getOverall();
            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/solar")
    public ResponseEntity<?> getDayOverallGenerated(@RequestBody SolarDataRequest request){
        try{
            OverallDayGeneratedResponse response = solarDataService.getOverallDayGenerated(request);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/savings")
    public ResponseEntity<?> getDayOverallSaved(@RequestBody SolarDataRequest request){
        try{
            OverallDaySavedResponse response = solarDataService.getOverallDaySaved(request);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/total/solar")
    public ResponseEntity<?> getTotalOverallGenerated(){
        try{
            OverallDayGeneratedResponse response = solarDataService.getOverallTotalGenerated();
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/total/saved")
    public ResponseEntity<?> getTotalOverallSaved(){
        try{
            OverallDaySavedResponse response = solarDataService.getOverallTotalSaved();
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
