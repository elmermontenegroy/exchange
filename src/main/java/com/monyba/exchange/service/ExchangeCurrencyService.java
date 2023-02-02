package com.monyba.exchange.service;

import com.monyba.exchange.dto.ExchangeCurrencyDTO;
import com.monyba.exchange.model.Exchange;

public interface ExchangeCurrencyService{

  ExchangeCurrencyDTO exchangeCurrency(Exchange exchange, Double amount);

}
