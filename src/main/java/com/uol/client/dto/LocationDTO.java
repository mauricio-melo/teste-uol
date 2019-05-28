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
public class LocationDTO {

    @JsonProperty("distance")
    private Long distance;

    @JsonProperty("title")
    private String title;

    @JsonProperty("woeid")
    private Long woeid;

    @JsonProperty("lon")
    private Double longitude;
}
