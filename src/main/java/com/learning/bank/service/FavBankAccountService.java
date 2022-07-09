package com.learning.bank.service;

import com.learning.bank.enums.ErrorCodes;
import com.learning.bank.exception.BusinessException;
import com.learning.bank.model.Bank;
import com.learning.bank.model.Customer;
import com.learning.bank.model.FavBankAccount;
import com.learning.bank.repository.FevBankAccountRepository;
import com.learning.bank.repository.BankRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FavBankAccountService {
    @Autowired
    FevBankAccountRepository fevBankAccountRepository;
    @Autowired
    CustomerService customerService;

    @Autowired
    BankRepository bankRepository;
    public FavBankAccount createBankAccount(Long customerId, FavBankAccount favBankAccount) {
        Customer customer = customerService.fetchCustomerById(customerId);
        long count = fevBankAccountRepository.countByCustomerCustomerIdAndDeleteInd(customerId, false);
        if(count > 20) {
            throw BusinessException.businessException(ErrorCodes.BANK_ACCOUNT_LIMIT_EXCEEDED);
        }
        Optional<FavBankAccount> alreadyExists = fevBankAccountRepository.findByIbanAndCustomerCustomerId(favBankAccount.getIban(), customerId);
        if(alreadyExists.isPresent())
            throw BusinessException.businessException(ErrorCodes.ALREADY_EXISTS);
        String bankCode = favBankAccount.getIban().substring(4, 8);
        log.info("IBAN: {}", bankCode);
        Optional<Bank> foundBank = bankRepository.findByCode(Long.valueOf(bankCode));
        if(foundBank.isPresent())
            favBankAccount.setBank(foundBank.get());
        else
            throw BusinessException.businessException(ErrorCodes.BANK_NOT_FOUND);
        fevBankAccountRepository.save(favBankAccount);
        return favBankAccount;
    }

    public FavBankAccount getAccountBalance(Long bankAccountId) {
        Optional<FavBankAccount> foundBankAccount = fevBankAccountRepository.findById(bankAccountId);
        if(foundBankAccount.isPresent())
            return foundBankAccount.get();
        else
            throw BusinessException.businessException(ErrorCodes.ACCOUNT_NOT_FOUND);

    }

    public void updateAccountBalance(FavBankAccount favBankAccount) {
        fevBankAccountRepository.save(favBankAccount);

    }
}
