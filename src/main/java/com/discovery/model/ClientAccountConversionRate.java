package com.discovery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ClientAccountConversionRate {

    private ClientAccount clientAccount;
    private CurrencyConversionRate currencyConversionRate;

}
