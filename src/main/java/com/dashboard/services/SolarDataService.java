package com.dashboard.services;


import com.dashboard.interfaces.IOverallRepository;
import com.dashboard.interfaces.ISolarRepository;
import com.dashboard.model.Repository.OverallModel;
import com.dashboard.model.Repository.SolarModel;
import com.dashboard.model.Request.SolarDataRequest;
import com.dashboard.model.Response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolarDataService {

    @Autowired
    private ISolarRepository iSolarRepository;

    @Autowired
    private IOverallRepository iOverallRepository;

    public List<SolarModel> getAll() throws Exception {
        try{
            List<SolarModel> list = iSolarRepository.findAll();
            return list;
        }catch(Exception e){
            System.out.println(e);
        }
        throw new Exception("Test failed!");
    }

    public List<SolarModel> getByDate(SolarDataRequest request) throws Exception {
        try{
            List<SolarModel> list = iSolarRepository.findByDate(request.getDate());
            return list;
        }catch(Exception e){
            System.out.println(e);
        }
        throw new Exception("Data of this date does not exist");
    }

    public SolarDataTotalResponse getTotalByDate(SolarDataRequest request) throws Exception {
        try{
            List<SolarModel> list = getByDate(request);
            double total = 0;
            for(SolarModel model : list){
                total += model.getWh();
            }
            return new SolarDataTotalResponse(total);
        }catch(Exception e){
            System.out.println(e);
        }
        throw new Exception("Total for this date can not be obtained");
    }

    public SolarCostSavedResponse getCostSavings(SolarDataRequest request) throws Exception{
        double cost = (double) 0.2411;
        try{
            SolarDataTotalResponse total = getTotalByDate(request);
            return new SolarCostSavedResponse(total.getTotalWh()/1000*cost);
        }catch (Exception e){
            System.out.println(e);
        }
        throw new Exception("Cost saved for this date can not be obtained");
    }

    public OverallPopulateResponse populateOverallTable(SolarDataRequest request) throws Exception {
        try{
            Optional<OverallModel> overallModelOptional = iOverallRepository.findByDate(request.getDate());
            if(overallModelOptional.isEmpty()){
                OverallModel model = new OverallModel();
                model.setDate(request.getDate());
                model.setCostSaved(getCostSavings(request).getCost());
                model.setTotalGenerated(getTotalByDate(request).getTotalWh());
                iOverallRepository.save(model);
                return new OverallPopulateResponse(model);

            }
            else{
                throw new Exception("Entry already exists!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        throw new Exception("Entry already exists!");
    }

    public List<OverallModel> getOverall() throws Exception {
        try{
            List<OverallModel> list = iOverallRepository.findAll();
            return list;
        }
        catch (Exception e){
            System.out.println(e);
        }

        throw new Exception("Cant fetch all overall data");
    }

    public OverallDayGeneratedResponse getOverallDayGenerated(SolarDataRequest request) throws Exception {
        try{
            OverallModel model = iOverallRepository.findByDate(request.getDate()).get();
            return new OverallDayGeneratedResponse(model.getTotalGenerated());

        }catch(Exception e){
            System.out.println(e);
        }

        throw new Exception("Cant fetch overall day generated data");
    }

    public OverallDaySavedResponse getOverallDaySaved(SolarDataRequest request) throws Exception {
        try{
            OverallModel model = iOverallRepository.findByDate(request.getDate()).get();
            return new OverallDaySavedResponse(model.getCostSaved());

        }catch(Exception e){
            System.out.println(e);
        }
        throw new Exception("Cant fetch overall day saved data");
    }

    public OverallDayGeneratedResponse getOverallTotalGenerated() throws Exception {
        try{
            List<OverallModel> list = iOverallRepository.findAll();
            OverallDayGeneratedResponse response = new OverallDayGeneratedResponse(0);
            for(int i = 0; i < list.size(); i++){
                response.setTotalWh(response.getTotalWh() + list.get(i).getTotalGenerated());
            }
            return response;

        }catch(Exception e){
            System.out.println(e);
        }

        throw new Exception("Cant fetch overall day generated data");
    }

    public OverallDaySavedResponse getOverallTotalSaved() throws Exception {
        try{
            List<OverallModel> list = iOverallRepository.findAll();
            OverallDaySavedResponse response = new OverallDaySavedResponse(0);
            for(int i = 0; i < list.size(); i++){
                response.setSaved(response.getSaved() + list.get(i).getCostSaved());
            }
            return response;

        }catch(Exception e){
            System.out.println(e);
        }

        throw new Exception("Cant fetch overall day generated data");
    }




}
