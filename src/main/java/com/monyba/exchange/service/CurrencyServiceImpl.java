package com.monyba.exchange.service;

import com.monyba.exchange.model.Currency;
import com.monyba.exchange.repository.CurrencyRepo;
import com.monyba.exchange.repository.GenericRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl extends CrudImpl<Currency, Integer> implements CurrencyService{

  private final CurrencyRepo repo;

  @Override
  protected GenericRepo<Currency, Integer> getRepository() {
    return repo;
  }

}
