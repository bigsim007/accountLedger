package za.co.bigsim.bankingApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.bigsim.bankingApi.entity.Account;
import za.co.bigsim.bankingApi.entity.User;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);
    Optional<Account> findByAccountNumberAndAccountType(String accountNumber, String accountType);
    Optional<Account> findByUser(User user);

}
