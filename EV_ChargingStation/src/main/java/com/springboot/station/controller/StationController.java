package com.springboot.station.controller;

import com.springboot.station.payload.ChargingStationRequestBean;
import com.springboot.station.payload.StationResponseBean;

import com.springboot.station.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class StationController
{
    @Autowired
    private StationService stationService;

    @GetMapping("/allStations")
    public StationResponseBean getAllStations() {
        StationResponseBean responseBean = stationService.getAllStations();
        System.out.println("ResponseBean: " + responseBean);
        return responseBean;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStation(@RequestBody ChargingStationRequestBean requestBean) throws Exception {

        boolean added = stationService.addChargingStation(requestBean);
        if (added) {
            return ResponseEntity.ok("Charging station added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding charging station");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteChargingStation(@PathVariable("id") Long id) throws Exception {
        boolean isDeleted = stationService.deleteChargingStation(id);
        if (isDeleted) {
            return ResponseEntity.ok("Charging station with ID " + id + " has been deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Charging station with ID " + id + " not found");
        }
    }

    @GetMapping("/show/{id}")
    public StationResponseBean getStationById(@PathVariable Long id) throws Exception {
        return stationService.getStationById(id);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<StationResponseBean> updateStationDetails(@PathVariable Long id,
                                                                    @RequestBody ChargingStationRequestBean stationRequestBean) throws Exception {
        StationResponseBean updatedStation = stationService.updateStationDetails(id, stationRequestBean);
        return ResponseEntity.ok(updatedStation);
    }

    @GetMapping("FreeStations")
    public StationResponseBean getAllFreeStations(){
        return new StationResponseBean();
    }
}
