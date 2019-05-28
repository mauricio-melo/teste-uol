package com.uol.client.integration;


import com.uol.client.vo.IpApiResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "IpApiClient", url = "${integration.ipApi-client.base-url}")
public interface IpApiClient {

    @GetMapping(path = "/{ip}", produces = APPLICATION_JSON_VALUE)
    IpApiResponseVO getLocalization(@PathVariable("ip") final String ip);
}