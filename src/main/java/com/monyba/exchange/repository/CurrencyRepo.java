package com.monyba.exchange.repository;

import com.monyba.exchange.model.Currency;
import com.monyba.exchange.model.Exchange;

import java.util.List;

public interface CurrencyRepo extends GenericRepo<Currency, Integer>{

  Currency findByCode(String code);

}
