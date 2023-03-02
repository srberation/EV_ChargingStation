package com.springboot.station.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StationResponseBean
{
    private Long stationId;
    private String stationName;
    private String stationImage;
    private Integer stationPricing;
    private String stationAddress;

    private List<StationDto> stations;
    private String message;
    public StationResponseBean(List<StationDto> stationDtos) {
        stations = stationDtos;
    }
}
