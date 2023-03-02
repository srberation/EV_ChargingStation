package com.springboot.station.repository;


import com.springboot.station.entity.BatteryStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<BatteryStation, Long>
{
    //
}
