package com.uol.client.config;

import com.uol.client.integration.IpApiClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {IpApiClient.class})
public class FeignConfig {

}
