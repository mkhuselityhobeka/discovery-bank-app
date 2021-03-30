package com.discovery.controller;

import com.discovery.exceptions.NoAccountToDisplayException;
import com.discovery.model.*;
import com.discovery.services.ClientAccountService;
import com.discovery.services.CurrencyAccountsConversionRateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1")
@Slf4j
public class ClientAccountController {

    ClientAccountService currencyService;
    CurrencyAccountsConversionRateService currencyAccountsConversionRateService;

    public ClientAccountController(ClientAccountService currencyService,CurrencyAccountsConversionRateService currencyAccountsConversionRateService){
        this.currencyService = currencyService;
        this.currencyAccountsConversionRateService = currencyAccountsConversionRateService;
    }

    /*get atm by id*/
    @GetMapping("{atmId}")
    public ResponseEntity<Atm> get(@PathVariable("atmId") Integer atmId){
        return new ResponseEntity<> (currencyService.getAtmId(atmId),HttpStatus.ACCEPTED);
    }


    /*get clients in DESC */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping("client/desc/{clientId}")
    public ResponseEntity<List<ClientAccount>>returnClientAccount(@PathVariable("clientId") Integer clientId) throws NoAccountToDisplayException {
           return new ResponseEntity<>(currencyService.getAccountByClientId(clientId),HttpStatus.ACCEPTED);
    }

    /*get clients in ASC*/
    @GetMapping("client/asc/{clientId}")
    public List<ClientAccount> returnCurrencyAccounts(@PathVariable("clientId") Integer clientId){
        return currencyAccountsConversionRateService.getCurrencyAccountBalance(clientId);
    }

    /*get clients id to use for withdrwal */
    @GetMapping("withdrawal/{clientId}")
    public List<ClientAccount> returnAccountListForWithdrawal(@PathVariable("clientId") Integer clientId){
        return currencyAccountsConversionRateService.getAccountListForWithdrawal (clientId);
    }

    /*from conversion/{clientId*/
    @GetMapping("{clientId}/{accountTypeCode}")
    public ResponseEntity<List<ClientAccount>>returnClientDatabyClientIdAndAccountTypeCode(@PathVariable("clientId") Integer clientId, @PathVariable("accountTypeCode") String accountTypeCode){
        return new ResponseEntity<>(currencyService.getAccountByClientIdAndAccountType (clientId,accountTypeCode),HttpStatus.ACCEPTED);
    }



    /*withdraw amount*/
    @PostMapping("withdraw")
    public String withdrawCash(@RequestBody WithDrawMoney drawCash){
       double cashwithDrwa= drawCash.getWithdrawalAmount();
       return currencyService.drawMoney(cashwithDrwa);
    }
}
