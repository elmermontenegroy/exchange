package com.monyba.exchange.controller;

import com.monyba.exchange.dto.ExchangeCurrencyDTO;
import com.monyba.exchange.model.Exchange;
import com.monyba.exchange.service.ExchangeCurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchangeCurrencies")
@RequiredArgsConstructor
public class ExchangeCurrencyController {

  private final ExchangeCurrencyService service;
  private final ModelMapper mapper;

  @PostMapping
  public ResponseEntity<ExchangeCurrencyDTO> exchangeCurrency(@Valid @RequestBody ExchangeCurrencyDTO dto) {
    Exchange exchange = mapper.map(dto, Exchange.class);
    return ResponseEntity.ok(service.exchangeCurrency(exchange, dto.getAmount()));
  }

}
