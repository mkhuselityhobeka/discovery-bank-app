package com.discovery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
@Table(name = "CURRENCY_CONVERSION_RATE")
public class CurrencyConversionRate {

    @Id
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    @Column(name = "CONVERSION_INDICATOR")
    private String conversionIndicator;
    @Column(name = "RATE")
    private double rate;
}
