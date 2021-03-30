package com.discovery.repository;

import com.discovery.model.CurrencyConversionRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyConversionRateRepo extends JpaRepository<CurrencyConversionRate,String> {

 }
