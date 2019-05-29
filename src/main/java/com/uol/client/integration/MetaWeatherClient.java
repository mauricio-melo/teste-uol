package com.uol.client.integration;


import com.uol.client.dto.LocationDTO;
import com.uol.client.dto.WeatherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "MetaWeatherClient", url = "${integration.metaWeather-client.base-url}")
public interface MetaWeatherClient {

    @GetMapping(path = "/location/search", produces = APPLICATION_JSON_VALUE)
    List<LocationDTO> getLocation(@RequestParam("lattlong") Double[] lattlong);

    @GetMapping(path = "/location/{woeid}/{year}/{month}/{day}", produces = APPLICATION_JSON_VALUE)
    List<WeatherDTO> getWeather(@PathVariable("woeid") Long woeid,
                                @PathVariable("year") Integer year,
                                @PathVariable("month") Integer month,
                                @PathVariable("day") Integer day);
}