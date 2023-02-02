package com.monyba.exchange.controller;

import com.monyba.exchange.dto.ExchangeDTO;
import com.monyba.exchange.exception.ModelNotFoundException;
import com.monyba.exchange.model.Exchange;
import com.monyba.exchange.service.ExchangeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class ExchangeController {

  private final ExchangeService service;
  private final ModelMapper mapper;

  @GetMapping
  public ResponseEntity<List<ExchangeDTO>> findAll() throws Exception {
    List<ExchangeDTO> list = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExchangeDTO> findById(@PathVariable("id") Integer id) throws Exception {
    Exchange exchange = service.findById(id);
    if(exchange == null) throw new ModelNotFoundException("Id not found: " + id);
    return new ResponseEntity<>(convertToDto(exchange), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ExchangeDTO> create(@Valid @RequestBody ExchangeDTO dto) throws Exception {
    Exchange exchange = service.save(convertToEntity(dto));
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(exchange.getIdExchange()).toUri();
    return ResponseEntity.created(location).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ExchangeDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody ExchangeDTO dto) throws Exception {
    dto.setIdExchange(id);
    Exchange exchange = service.findById(dto.getIdExchange());
    if(exchange == null) throw new ModelNotFoundException("Id not found: "+ dto.getIdExchange());
    exchange = service.update(convertToEntity(dto));
    return new ResponseEntity<>(convertToDto(exchange), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
    Exchange exchange = service.findById(id);
    if(exchange == null) throw new ModelNotFoundException("Id not found: " + id);
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


  private ExchangeDTO convertToDto(Exchange entity){
    return mapper.map(entity, ExchangeDTO.class);
  }

  private Exchange convertToEntity(ExchangeDTO dto){
    return mapper.map(dto, Exchange.class);
  }
  
}
