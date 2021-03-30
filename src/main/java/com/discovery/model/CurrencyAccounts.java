package com.discovery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CurrencyAccounts {


    private String clientAccountNumber;
    private String accountTypeCode;
    private CurrencyConversionRate currencyCode;
    private double displayBalance;
    private double sa_rands;

    }

