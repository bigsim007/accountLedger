package za.co.bigsim.bankingApi.service;

import za.co.bigsim.bankingApi.entity.dto.AccountDto;
import za.co.bigsim.bankingApi.entity.Account;
import za.co.bigsim.bankingApi.entity.User;

import java.util.Optional;

public interface AccountService {

    public AccountDto findAccount(User user);

    public Account saveAccount(AccountDto account);

    public Account updateAccount(Account account);

    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByAccountNumberAndAccountType(String accountNumber, String accountType);

    public AccountDto mapToUserDto(Account account);
}
