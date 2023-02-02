package com.monyba.exchange.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CurrencyDTO {

  private Integer idCurrency;
  @NotNull
  @Size(min = 5, max = 100)
  private String name;
  @NotNull
  @Size(min = 3)
  private String code;

}
