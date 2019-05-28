package com.uol.client.web.controller;

import com.uol.client.dto.ClientDTO;
import com.uol.client.service.ClientService;
import com.uol.client.vo.ClientRequestVO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(ClientController.CLIENT_ENDPOINT)
public class ClientController {

    public static final String CLIENT_ENDPOINT = "/client";
    private final ClientService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all clients with pagination", response = ClientDTO.class)
    public Page<ClientDTO> findAll(final ClientDTO filter,
                                 @PageableDefault @SortDefault final Pageable pageable) {
        return this.service.findAll(filter, pageable);
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Searching for a specific client", response = ClientDTO.class)
    public ResponseEntity<ClientDTO> findGroupProfileById(@Valid @PathVariable final Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creating a new client", responseReference = "New resource created.")
    public ResponseEntity<Void> create(@Valid @RequestBody ClientRequestVO clientRequestVO, HttpServletRequest request) {
        final ClientDTO dto = this.service.save(this.voToDTO(clientRequestVO), request.getRemoteAddr());
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updating a client", responseReference = "200 = Resource updated")
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientRequestVO vo,
                                                   @PathVariable("id") Long id) {
        return ResponseEntity.ok(this.service.update(id, this.voToDTO(vo)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleting a client", responseReference = "200 = Resource deleted")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

    private ClientDTO voToDTO(ClientRequestVO vo) {
        return ClientDTO.builder()
                .name(vo.getName())
                .age(vo.getAge())
                .build();
    }
}
