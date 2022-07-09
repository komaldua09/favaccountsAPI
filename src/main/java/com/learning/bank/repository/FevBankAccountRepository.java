package com.learning.bank.repository;

import com.learning.bank.model.FavBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FevBankAccountRepository extends JpaRepository<FavBankAccount, Long> {
    long countByCustomerCustomerIdAndDeleteInd(Long customerId, boolean deleteInd);

    Optional<FavBankAccount> findByIbanAndCustomerCustomerId(String iban, Long customerId);
}
