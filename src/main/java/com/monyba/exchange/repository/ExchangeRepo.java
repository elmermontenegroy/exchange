package com.monyba.exchange.repository;

import com.monyba.exchange.model.Currency;
import com.monyba.exchange.model.Exchange;

import java.time.LocalDate;

public interface ExchangeRepo extends GenericRepo<Exchange, Integer>{

  Exchange findByOriginCurrencyAndDestinationCurrencyAndAndDate(Currency originCurrency, Currency destinationCurrency, LocalDate date);

}
