package com.uol.client.service;

import com.uol.client.domain.Client;
import com.uol.client.dto.ClientDTO;
import com.uol.client.exception.ResourceNotFoundException;
import com.uol.client.mapper.ClientMapper;
import com.uol.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.Example.of;

@Service
@RequiredArgsConstructor(onConstructor = @__({ @Lazy}))
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;

    public Page<ClientDTO> findAll(final ClientDTO filter, final Pageable pageable) {
        final Page<Client> result = this.repository.findAll(of(mapper.toEntity(filter)), pageable);
        return new PageImpl<>(mapper.toDTOList(result.getContent()), pageable, result.getTotalElements());
    }

    public ClientDTO findById(final Long id){
        return this.mapper.toDTO(
                Optional.ofNullable(this.repository.getOne(id))
                        .orElseThrow(() -> new ResourceNotFoundException(id.toString())));
    }

    public ClientDTO save(final ClientDTO dto) {
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
