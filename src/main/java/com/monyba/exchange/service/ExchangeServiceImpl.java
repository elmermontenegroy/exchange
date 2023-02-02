package com.monyba.exchange.service;

import com.monyba.exchange.model.Exchange;
import com.monyba.exchange.repository.ExchangeRepo;
import com.monyba.exchange.repository.GenericRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl extends CrudImpl<Exchange, Integer> implements ExchangeService {

  private final ExchangeRepo repo;

  @Override
  protected GenericRepo<Exchange, Integer> getRepository() {
    return repo;
  }

}
