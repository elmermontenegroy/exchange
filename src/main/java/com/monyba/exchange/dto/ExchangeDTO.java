package com.monyba.exchange.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


@Data
public class ExchangeDTO {

  private Integer idExchange;
  @NotNull
  private CurrencyDTO originCurrency;
  @NotNull
  private CurrencyDTO destinationCurrency;
  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate date;
  @NotNull
  private Double exchangeRate;

}
