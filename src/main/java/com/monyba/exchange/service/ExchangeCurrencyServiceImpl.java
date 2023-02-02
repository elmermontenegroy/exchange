package com.monyba.exchange.service;

import com.monyba.exchange.dto.ExchangeCurrencyDTO;
import com.monyba.exchange.model.Currency;
import com.monyba.exchange.model.Exchange;
import com.monyba.exchange.repository.CurrencyRepo;
import com.monyba.exchange.repository.ExchangeRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeCurrencyServiceImpl implements ExchangeCurrencyService {

  private final ModelMapper mapper;
  private final CurrencyRepo currencyRepo;
  private final ExchangeRepo exchangeRepo;

  @Override
  public ExchangeCurrencyDTO exchangeCurrency(Exchange exchange, Double amount) {
    Currency originCurrency = currencyRepo.findByCode(exchange.getOriginCurrency().getCode());
    Currency destinationCurrency = currencyRepo.findByCode(exchange.getDestinationCurrency().getCode());
    LocalDate date = Optional.ofNullable(exchange.getDate()).orElse(LocalDate.now());
    exchange = exchangeRepo.findByOriginCurrencyAndDestinationCurrencyAndAndDate(originCurrency, destinationCurrency, date);
    ExchangeCurrencyDTO exchangeCurrency = mapper.map(exchange, ExchangeCurrencyDTO.class);
    exchangeCurrency.setAmount(amount);
    exchangeCurrency.setAmountWithExchangeRate(amount*exchangeCurrency.getExchangeRate());
    return exchangeCurrency;
  }

}
