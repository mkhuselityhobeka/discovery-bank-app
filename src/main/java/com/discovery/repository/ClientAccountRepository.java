package com.discovery.repository;

import com.discovery.model.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ClientAccountRepository extends JpaRepository<ClientAccount,String> {
             List<ClientAccount>findByClientIdOrderByDisplayBalanceDesc(Integer clientId);
             List<ClientAccount>findByClientIdOrderByDisplayBalanceAsc(Integer clientId);
             List<ClientAccount>findByClientIdAndAccountTypeCode(Integer clientId, String accountTypeCode);
             List<ClientAccount> findByClientAccountNumber(String clientAccountNumber);
}
