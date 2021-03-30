package com.discovery.services;

import com.discovery.exceptions.AtmNotRegisteredException;
import com.discovery.exceptions.NoAccountToDisplayException;
import com.discovery.model.*;
import com.discovery.repository.AtmRepository;
import com.discovery.repository.ClientAccountRepository;
import com.discovery.repository.DenominationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ClientAccountService {

    ClientAccountRepository clientAccountRepository;
    ClientAccount clientAccount;
    CurrencyAccountsConversionRateService currencyAccountsConversionRateService;
    AtmRepository atmRepository;
    DenominationRepository denominationRepository;
    Atm atmData;
    Denomination denomination;
    String accountType = "";
    List<ClientAccount> accountList = new ArrayList<> ();
    int count = 0;
    static double amountInbank;
    double availbleAmountInbank;

    static List<Double> values = new ArrayList<> ();
    static List<Denomination> denominations = new ArrayList<> ();
    static List<Integer> counts = new ArrayList<> ();
    protected int[] countingNotes = {0, 0, 0, 0, 0};



    public ClientAccountService(ClientAccountRepository clientAccountRepository, ClientAccount clientAccount,
                                CurrencyAccountsConversionRateService currencyAccountsConversionRateService,
                                Denomination denomination, Atm atmData,
                                DenominationRepository denominationRepository,AtmRepository atmRepository) {

        this.clientAccountRepository = clientAccountRepository;
        this.clientAccount = clientAccount;
        this.currencyAccountsConversionRateService = currencyAccountsConversionRateService;

        this.denomination = denomination;
        this.atmData = atmData;
        this.denominationRepository = denominationRepository;
        this.atmRepository = atmRepository;

    }

    /*return atm list by atmId */
    public Atm getAtmId(Integer atmId) throws AtmNotRegisteredException {
        Optional<Atm>optionalAtm = atmRepository.findById(atmId);
        if (optionalAtm.isPresent()){
            atmData =  optionalAtm.get();
            return atmData;
        }
        throw new AtmNotRegisteredException ("Atm Not registered");
    }
    /*return account list by ClientIdAndAccountTypeCode */
    public List<ClientAccount>getAccountByClientIdAndAccountType(Integer clientId, String accountTypeCode){
        accountType = accountTypeCode;
        accountList = clientAccountRepository.findByClientIdAndAccountTypeCode(clientId,accountTypeCode);
        return accountList;
    }

    public List<ClientAccount> getAccountByClientId(Integer clientId) throws NoAccountToDisplayException {
        List<ClientAccount> clientAccountList = clientAccountRepository.findByClientIdOrderByDisplayBalanceDesc (clientId);
        if (clientAccountList.isEmpty ()) {
            throw new NoAccountToDisplayException();
        }
        return clientAccountRepository.findByClientIdOrderByDisplayBalanceDesc (clientId);
    }


    /*find by account number*/
    public List<ClientAccount> getByclientAccount(String clientAccountNumber) {
        return clientAccountRepository.findByClientAccountNumber(clientAccountNumber);
    }


    /*get total amount in bank*/
    public  Double calculateTotalAmountInAtm(List<Double>values, List<Integer>counts) {
        for (int k = 0; k < values.size (); k++) {
            amountInbank = amountInbank + values.get(k) * counts.get(k);
            System.out.println ("amountInbank is" + amountInbank);
        }
        return amountInbank;
    }


    /*return denomination data*/
    public void returnAtmAllocation(double withdrawalAmount) {

        List<AtmAllocation> atmAllocationlist = atmData.getAtmAllocationId();
        for (AtmAllocation atmAllocation : atmAllocationlist) {
            denomination = atmAllocation.getDenominationId();
            values = Arrays.asList (denomination.getValue ());
            counts = Arrays.asList (atmAllocation.getCount());
            availbleAmountInbank =  calculateTotalAmountInAtm(values,counts);
            checkAtmAvailbleBalnce(withdrawalAmount);
        }
    }

    public Double calculateAcountBalanceAfterWithdrwal(double withdrawalAmount) {
        double balance;
        for (ClientAccount account : accountList) {
            List<ClientAccount> getByAccountNumberList = getByclientAccount(account.getClientAccountNumber());
            for (ClientAccount clientAccount : getByAccountNumberList) {
                if (clientAccount.getAccountTypeCode().equals(accountType)) {
                    if (withdrawalAmount <= clientAccount.getDisplayBalance()) {
                        balance = clientAccount.getDisplayBalance() - withdrawalAmount;
                        clientAccount.setDisplayBalance(balance);
                      //  clientAccountRepository.save(clientAccount);
                        return balance;
                    } else {
                        return null;
                    }
                }

            }
        }
        return null;
    }

    public void checkAtmAvailbleBalnce(double withdrawalAmount) {
        if(withdrawalAmount <= availbleAmountInbank) {
            for (int i = 0; i < values.size(); i++) {
                if(values.get(i) <= withdrawalAmount) {
                    int countNotes = (int) (withdrawalAmount / values.get (i));
                    if (counts.get(i) > 0){
                        countingNotes[i] = countNotes >= counts.get(i) ? counts.get(i) : countNotes;
                        count = countNotes >= counts.get(i) ? 0 : counts.get(i) - countNotes;
                        availbleAmountInbank = availbleAmountInbank - withdrawalAmount;
                        calculateAcountBalanceAfterWithdrwal(withdrawalAmount);
                    }
                }
            }
            displayNotes ();
            displayLeftNotes();
        } else {
            System.out.println ("Unable to dispense cash at this moment for this big amount");
        }

    }


    /*withdrwal */
    public String drawMoney(double withdrawalAmount){
        returnAtmAllocation(withdrawalAmount);
        return null;
    }


    public void displayNotes() {
        for (int i = 0; i < countingNotes.length; i++) {
            if (countingNotes[i] != 0) {
                System.out.println (values.get (i) + " * " + countingNotes[i] + " = " + (values.get(i) * countingNotes[i]));
            }
          }
       }

    private void displayLeftNotes() {
        for (int i = 0; i < values.size (); i++) {
            System.out.println("Notes of " + values.get(i) + " left are " + count);
        }

    }

}
