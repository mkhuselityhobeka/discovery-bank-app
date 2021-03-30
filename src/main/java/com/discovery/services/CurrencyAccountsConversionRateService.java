package com.discovery.services;

import com.discovery.model.ClientAccount;
import com.discovery.model.CurrencyAccounts;
import com.discovery.model.CurrencyConversionRate;
import com.discovery.repository.ClientAccountRepository;
import com.discovery.repository.CurrencyConversionRateRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyAccountsConversionRateService {

         CurrencyConversionRateRepo rateRepo;
         CurrencyConversionRate currencyConversionRate;
         ClientAccountRepository clientAccountRepository;
         CurrencyAccounts currencyAccounts;
         double ZARAMOUT;

         List<ClientAccount> accountList = new ArrayList<> ();

          public CurrencyAccountsConversionRateService(CurrencyConversionRateRepo rateRepo,CurrencyConversionRate currencyConversionRate,
                                                       ClientAccountRepository clientAccountRepository,CurrencyAccounts currencyAccounts){
              this.rateRepo= rateRepo;
              this.currencyConversionRate=currencyConversionRate;
              this.clientAccountRepository=clientAccountRepository;
              this.currencyAccounts=currencyAccounts;
          }

          public  List<ClientAccount> getAccountListForWithdrawal(Integer clientId){
              return  getCurrencyAccountBalance(clientId);
          }

         public List<ClientAccount> getCurrencyAccountBalance(Integer clientId) {

             accountList= clientAccountRepository.findByClientIdOrderByDisplayBalanceAsc(clientId);
                    double rate = 0;
             for (ClientAccount account : accountList) {
                 CurrencyConversionRate conversionRate = account.getCurrencyCode ();
                 rate = conversionRate.getRate ();
                 if((conversionRate.getCurrencyCode().equals("EUR")) || (conversionRate.getCurrencyCode().equals("GBP"))
                         || (conversionRate.getCurrencyCode().equals("USD"))){
                     ZARAMOUT =  account.getDisplayBalance () * rate;
                 }else {
                     ZARAMOUT  = account.getDisplayBalance () / rate;
                 }

                 account.setSaRands (ZARAMOUT);
                 currencyAccounts.setSa_rands (ZARAMOUT);


             }
             return accountList;
       }
}
