package com.monyba.exchange.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ExchangeCurrencyDTO {
  @NotNull
  private Double amount;
  @NotNull
  @Size(min = 3)
  private String codeOriginCurrency;
  @NotNull
  @Size(min = 3)
  private String codeDestinationCurrency;
  private Double amountWithExchangeRate;
  private Double exchangeRate;
}
