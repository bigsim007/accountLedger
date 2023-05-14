package za.co.bigsim.bankingApi.service.impl;

import org.springframework.stereotype.Service;
import za.co.bigsim.bankingApi.entity.dto.AccountDto;
import za.co.bigsim.bankingApi.entity.Account;
import za.co.bigsim.bankingApi.entity.User;
import za.co.bigsim.bankingApi.repository.AccountRepository;
import za.co.bigsim.bankingApi.service.AccountService;
import za.co.bigsim.bankingApi.service.UserService;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    private UserService userService;

    public AccountServiceImpl(AccountRepository accountRepository, UserService userService){
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    @Override
    public AccountDto findAccount(User user) {

        Optional<Account> account = accountRepository.findByUser(user);

        if(account.isPresent()){
            return mapToUserDto(account.get());
        }

        return  null;
    }

    @Override
    public Account saveAccount(AccountDto account) {

        Account newAccount = new Account();
        newAccount.setAccountNumber(account.getAccountNumber());
        newAccount.setBankName(account.getBankName());
        newAccount.setBranchNumber(account.getBranchNumber());
        newAccount.setCurrentBalance(account.getCurrentBalance());
        newAccount.setAccountType(account.getAccountType());
        newAccount.setUser(userService.getUserfromDto(account.getUser()));
        accountRepository.save(newAccount);

        return newAccount;
    }

    @Override
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Optional<Account> findByAccountNumberAndAccountType(String accountNumber, String accountType) {
        return accountRepository.findByAccountNumberAndAccountType(accountNumber, accountType);
    }

    @Override
    public AccountDto mapToUserDto(Account account){
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setBankName(account.getBankName());
        accountDto.setBranchNumber(account.getBranchNumber());
        accountDto.setCurrentBalance(account.getCurrentBalance());
        accountDto.setAccountType(account.getAccountType());
        account.setUser(account.getUser());
        return accountDto;
    }
}
