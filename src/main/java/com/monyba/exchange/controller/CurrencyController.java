package com.monyba.exchange.controller;

import com.monyba.exchange.dto.CurrencyDTO;
import com.monyba.exchange.exception.ModelNotFoundException;
import com.monyba.exchange.model.Currency;
import com.monyba.exchange.service.CurrencyService;
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
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {

  private final CurrencyService service;
  private final ModelMapper mapper;

  @GetMapping
  public ResponseEntity<List<CurrencyDTO>> findAll() throws Exception {
    List<CurrencyDTO> list = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CurrencyDTO> findById(@PathVariable("id") Integer id) throws Exception {
    Currency currency = service.findById(id);
    if(currency == null) throw new ModelNotFoundException("Id not found: " + id);
    return new ResponseEntity<>(convertToDto(currency), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<CurrencyDTO> create(@Valid @RequestBody CurrencyDTO dto) throws Exception {
    Currency currency = service.save(convertToEntity(dto));
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(currency.getIdCurrency()).toUri();
    return ResponseEntity.created(location).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<CurrencyDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody CurrencyDTO dto) throws Exception {
    dto.setIdCurrency(id);
    Currency currency = service.findById(dto.getIdCurrency());
    if(currency == null) throw new ModelNotFoundException("Id not found: "+ dto.getIdCurrency());
    currency = service.update(convertToEntity(dto));
    return new ResponseEntity<>(convertToDto(currency), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
    Currency currency = service.findById(id);
    if(currency == null) throw new ModelNotFoundException("Id not found: " + id);
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


  private CurrencyDTO convertToDto(Currency entity){
    return mapper.map(entity, CurrencyDTO.class);
  }

  private Currency convertToEntity(CurrencyDTO dto){
    return mapper.map(dto, Currency.class);
  }

}
