package com.springboot.station.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChargingStationRequestBean {
    private Long stationId;
    private String stationName;
    private String stationImage;
    private Integer stationPricing;
    private String stationAddress;
}
