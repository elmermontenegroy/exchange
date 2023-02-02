package com.monyba.exchange.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Exchange {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idExchange;
  @ManyToOne
  @JoinColumn(name = "id_origin_currency", nullable = false, foreignKey = @ForeignKey(name = "fk_exchange_origin_currency"))
  private Currency originCurrency;
  @ManyToOne
  @JoinColumn(name = "id_destination_currency", nullable = false, foreignKey = @ForeignKey(name = "fk_exchange_destination_currency"))
  private Currency destinationCurrency;
  @Column(nullable = false)
  private LocalDate date;
  @Column(nullable = false)
  private Double exchangeRate;

}
