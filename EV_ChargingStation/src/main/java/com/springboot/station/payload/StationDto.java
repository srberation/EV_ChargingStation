package com.springboot.station.payload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class StationDto
{

    private Long stationId;
    private String stationName;
    private String stationImage;
    private Integer stationPricing;
    private String stationAddress;
}
