package com.springboot.station.service.impl;


import com.springboot.station.entity.BatteryStation;
import com.springboot.station.exception.ResourceNotFoundException;
import com.springboot.station.payload.ChargingStationRequestBean;
import com.springboot.station.payload.StationDto;
import com.springboot.station.payload.StationResponseBean;
import com.springboot.station.repository.StationRepository;

import com.springboot.station.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StationServiceImpl implements StationService
{
    @Autowired
    private  StationRepository stationRepository;

    @Override
    public StationResponseBean getAllStations() {
        List<BatteryStation> stationEntities = stationRepository.findAll();
        for(BatteryStation e: stationEntities){
            System.out.println("stationEntities: " + e);
        }
        List<StationDto> stationDtos = new ArrayList<>();
        for (BatteryStation stationEntity : stationEntities) {
            StationDto stationDto = new StationDto(
                    stationEntity.getStationId(),
                    stationEntity.getStationName(),
                    stationEntity.getStationImage(),
                    stationEntity.getStationPricing(),
                    stationEntity.getStationAddress()
            );
            stationDtos.add(stationDto);

        }
        for(StationDto e: stationDtos){
            System.out.println("stationDTO: " + e);
        }
        StationResponseBean responseBean = new StationResponseBean();
        responseBean.setStations(stationDtos);
        return responseBean;
    }

    @Override
    public boolean addChargingStation(ChargingStationRequestBean stationRequestBean) throws Exception {
        try {
            System.out.println("stationRequestBean: " + stationRequestBean);
            if(stationRequestBean!= null){
                BatteryStation stationEntity = new BatteryStation();
                stationEntity.setStationName( stationRequestBean.getStationName() != null ?stationRequestBean.getStationName() : "");
                stationEntity.setStationImage( stationRequestBean.getStationImage() != null ? stationRequestBean.getStationImage(): "");
                stationEntity.setStationPricing( stationRequestBean.getStationPricing());
                stationEntity.setStationAddress( stationRequestBean.getStationAddress() != null ? stationRequestBean.getStationAddress():"");
                System.out.println("stationEntity: " + stationEntity);
                stationRepository.save(stationEntity);
                return true;
            }
            else
                return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw e;
        }

    }

    @Override
    public boolean deleteChargingStation(Long id) throws Exception {
        Optional<BatteryStation> optionalChargingStation = stationRepository.findById(id);
        if (optionalChargingStation.isPresent()) {
            BatteryStation chargingStation = optionalChargingStation.get();
            stationRepository.delete(chargingStation);
            return true;
        }
        return false;
    }

    @Override
    public StationResponseBean getStationById(Long id) throws Exception {
        BatteryStation stationEntity = stationRepository.findById(id).orElse(null);
        StationResponseBean responseBean = new StationResponseBean();
        System.out.println("responseBean : " + responseBean);
        if (stationEntity != null) {
            responseBean.setStationId(stationEntity.getStationId());
            responseBean.setStationName(stationEntity.getStationName());
            responseBean.setStationImage(stationEntity.getStationImage());
            responseBean.setStationPricing(stationEntity.getStationPricing());
            responseBean.setStationAddress(stationEntity.getStationAddress());
        } else {
            responseBean.setMessage("Station Id does not exist");
        }
        return responseBean;
    }

    @Override
    public StationResponseBean updateStationDetails(Long id, ChargingStationRequestBean stationRequestBean) throws Exception {
        System.out.println("stationBean:" + stationRequestBean );
        Optional<BatteryStation> optionalStationEntity = stationRepository.findById(id);
        if (!optionalStationEntity.isPresent()) {
            throw new ResourceNotFoundException("Station with ID " + id + " not found.");

        }
        BatteryStation stationEntity = optionalStationEntity.get();
        // Update the stationEntity object with the new details
        if (stationRequestBean.getStationName() != null) {
            stationEntity.setStationName(stationRequestBean.getStationName());
        }
        if (stationRequestBean.getStationImage() != null) {
            stationEntity.setStationImage(stationRequestBean.getStationImage());
        }
        if (stationRequestBean.getStationPricing()!= null){
            stationEntity.setStationPricing(stationRequestBean.getStationPricing());
        }
        if (stationRequestBean.getStationAddress() != null) {
            stationEntity.setStationAddress(stationRequestBean.getStationAddress());
        }
        // Save the updated stationEntity to the database
        System.out.println("StationEntity:" + stationEntity);
        stationRepository.save(stationEntity);
        StationResponseBean responseBean = new StationResponseBean();
        if (stationEntity != null){
            responseBean.setStationId(stationEntity.getStationId());
            responseBean.setStationName(stationEntity.getStationName());
            responseBean.setStationImage(stationEntity.getStationImage());
            responseBean.setStationPricing(stationEntity.getStationPricing());
            responseBean.setStationAddress(stationEntity.getStationAddress());
        }
        System.out.println("ResponseBean:" + responseBean);
        return responseBean;
    }
}
