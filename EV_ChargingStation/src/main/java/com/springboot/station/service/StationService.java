package com.springboot.station.service;


import com.springboot.station.payload.ChargingStationRequestBean;
import com.springboot.station.payload.StationResponseBean;

public interface StationService
{
    StationResponseBean getAllStations();
    boolean addChargingStation(ChargingStationRequestBean stationEntity) throws Exception;
    boolean deleteChargingStation(Long id) throws  Exception;
    StationResponseBean getStationById(Long id) throws Exception;
    StationResponseBean updateStationDetails(Long id, ChargingStationRequestBean stationRequestBean) throws Exception;
}
