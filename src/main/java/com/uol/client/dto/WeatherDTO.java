package com.uol.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherDTO {

    @JsonProperty("min_temp")
    private Double minimum;

    @JsonProperty("max_temp")
    private Double maximum;
}
