package com.uol.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsolidatedWeatherDTO {

    @JsonProperty("consolidated_weather")
    private List<WeatherDTO> weather;

}
