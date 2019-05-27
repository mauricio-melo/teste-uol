package com.uol.client.mapper;

import com.uol.client.commons.AbstractMapper;
import com.uol.client.domain.Client;
import com.uol.client.dto.ClientDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientMapper extends AbstractMapper<Client, ClientDTO> {

    @Override
    public ClientDTO toDTO(Client entity) {
        return super.toDTO(entity);
    }

    @Override
    public List<ClientDTO> toDTOList(List<Client> entity) {
        return super.toDTOList(entity);
    }

    @Override
    public Client toEntity(ClientDTO dto) {
        return super.toEntity(dto);
    }
}