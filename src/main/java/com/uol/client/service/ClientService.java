package com.uol.client.service;

import com.uol.client.domain.Client;
import com.uol.client.dto.ClientDTO;
import com.uol.client.dto.IpApiDTO;
import com.uol.client.dto.LocationDTO;
import com.uol.client.dto.WeatherDTO;
import com.uol.client.exception.ResourceNotFoundException;
import com.uol.client.integration.IpApiClient;
import com.uol.client.integration.MetaWeatherClient;
import com.uol.client.mapper.ClientMapper;
import com.uol.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static org.springframework.data.domain.Example.of;

@Service
@RequiredArgsConstructor(onConstructor = @__({ @Lazy}))
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;
    private final IpApiClient ipApiClient;
    private final MetaWeatherClient metaWeatherClient;

    public Page<ClientDTO> findAll(final ClientDTO filter, final Pageable pageable) {
        final Page<Client> result = this.repository.findAll(of(mapper.toEntity(filter)), pageable);
        return new PageImpl<>(mapper.toDTOList(result.getContent()), pageable, result.getTotalElements());
    }

    public ClientDTO findById(final Long id){
        return this.mapper.toDTO(
                Optional.of(this.repository.getOne(id))
                        .orElseThrow(() -> new ResourceNotFoundException(id.toString())));
    }

    public ClientDTO save(final ClientDTO dto, String remoteAddr) {

        final IpApiDTO ipApiDTO = this.ipApiClient.getLocalization("201.6.1.20");

        final Double[] data = {ipApiDTO.getLatitude(), ipApiDTO.getLongitude()};

        final List<LocationDTO> locationDTOS = this.metaWeatherClient.getLocation(data);

        final LocalDate date = LocalDate.now();
        final List<WeatherDTO> weather = this.metaWeatherClient.getWeather(locationDTOS.get(0).getWoeid(), date.getYear(), date.getMonthValue(), date.getDayOfMonth());

        dto.setMaximumWeather((int) Math.round(weather.stream().max(Comparator.comparing(WeatherDTO::getMaximum)).get().getMaximum()));
        dto.setMinimumWeather((int) Math.round(weather.stream().min(Comparator.comparing(WeatherDTO::getMinimum)).get().getMinimum()));

        final Client client = this.mapper.toEntity(dto);
        return this.mapper.toDTO(this.repository.save(client));
    }

    public ClientDTO update(final Long id, final ClientDTO dto) {
        final Client client = this.mapper.toEntity(this.findById(id));
        client.setName(dto.getName());
        client.setAge(dto.getAge());
        return this.mapper.toDTO(this.repository.save(client));
    }

    public void delete(final Long id) {
        this.repository.delete(this.mapper.toEntity(this.findById(id)));
    }
}
