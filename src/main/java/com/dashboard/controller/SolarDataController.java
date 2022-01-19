package com.dashboard.controller;

import com.dashboard.model.Repository.SolarModel;
import com.dashboard.model.Request.SolarDataRequest;
import com.dashboard.model.Response.OverallPopulateResponse;
import com.dashboard.model.Response.SolarCostSavedResponse;
import com.dashboard.model.Response.SolarDataTotalResponse;
import com.dashboard.services.SolarDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class SolarDataController {

    @Autowired
    SolarDataService solarDataService;
    @GetMapping("/test")
    public void test(){
        System.out.printf("test");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        try{
            List<SolarModel> list = solarDataService.getAll();
            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<?> getByDate(@RequestBody SolarDataRequest request){
        try{
            List<SolarModel> list = solarDataService.getByDate(request);
            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/total")
    public ResponseEntity<?> getTotalByDate (@RequestBody SolarDataRequest request){
        try{
            SolarDataTotalResponse response = solarDataService.getTotalByDate(request);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saved")
    public ResponseEntity<?>  getCostSaved (@RequestBody SolarDataRequest request){
        try{
            SolarCostSavedResponse response = solarDataService.getCostSavings(request);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/populate")
    public ResponseEntity<?> populateOverallTable(@RequestBody SolarDataRequest request){
        try{
            OverallPopulateResponse response = solarDataService.populateOverallTable(request);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }




}
