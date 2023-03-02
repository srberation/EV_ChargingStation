package com.springboot.station.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "battery_station")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BatteryStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private long stationId;

    @Column(name = "station_name")
    private String stationName;

    @Column(name = "station_image")
    private String stationImage;

    @Column(name = "station_pricing")
    private Integer stationPricing;

    @Column(name = "station_address")
    private String stationAddress;
}
