package za.co.bigsim.bankingApi.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.bigsim.bankingApi.entity.Account;
import za.co.bigsim.bankingApi.entity.Transaction;
import za.co.bigsim.bankingApi.entity.dto.TransactionDto;
import za.co.bigsim.bankingApi.entity.dto.TransactionType;
import za.co.bigsim.bankingApi.repository.TransactionRepository;
import za.co.bigsim.bankingApi.service.AccountService;
import za.co.bigsim.bankingApi.service.TransactionService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private AccountService accountService;

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountService accountService, TransactionRepository transactionRepository){
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionDto> findTransactionsByAccountNumber(String accountNumber) {
        List<TransactionDto> transactionDtos = new ArrayList<>();
        Optional<Account> accountEntityOpt = accountService.findByAccountNumber(accountNumber);
        if(accountEntityOpt.isPresent()) {
            Optional<List<Transaction>> transactionEntitiesOpt = transactionRepository.findByAccountNumber(accountNumber);
            if(transactionEntitiesOpt.isPresent()) {
                transactionEntitiesOpt.get().forEach(transaction -> {
                    transactionDtos.add(mapToTransaction(transaction));
                });
            }
        }

        return transactionDtos;
    }

    @Override
    public List<TransactionDto> findTransactionsByAccountNumberAndAccountType(String accountNumber, String accountType) {
        List<TransactionDto> transactionDetails = new ArrayList<>();
        Optional<Account> accountEntityOpt = accountService.findByAccountNumberAndAccountType(accountNumber, accountType);
        if(accountEntityOpt.isPresent()) {
            Optional<List<Transaction>> transactionEntitiesOpt = transactionRepository.findByAccountNumber(accountNumber);
            if(transactionEntitiesOpt.isPresent()) {
                transactionEntitiesOpt.get().forEach(transaction -> {
                    transactionDetails.add(mapToTransaction(transaction));
                });
            }
        }

        return transactionDetails;
    }

    @Transactional
    @Override
    public Object creditAccount(BigDecimal amount, String accountNumber) {
        Optional<Account> accountEntityOpt = accountService.findByAccountNumber(accountNumber);

        if(accountEntityOpt.isPresent()){
            createTransaction(accountNumber, amount, TransactionType.CREDIT);
            Account account = accountEntityOpt.get();
            BigDecimal a = account.getCurrentBalance().subtract(amount);
            account.setCurrentBalance(a);
            accountService.updateAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body("Success: amount credit succesfully" + accountNumber);

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account Number " + accountNumber + " not found.");

    }

    public Transaction createTransaction(String accountNumber, BigDecimal amount, TransactionType txType) {

        Transaction transaction = new Transaction();
        transaction.setReference("");
        transaction.setTransactionType(txType);
        transaction.setTrxDate(new Date());
        transaction.setInitiationDate(new Date());
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);

        return transactionRepository.save(transaction);

    }

    @Transactional
    @Override
    public Object debitAccount(BigDecimal amount, String accountNumber) {
        Optional<Account> accountEntityOpt = accountService.findByAccountNumber(accountNumber);

        if(accountEntityOpt.isPresent()){
            createTransaction(accountNumber, amount, TransactionType.DEBIT);
            Account account = accountEntityOpt.get();
            BigDecimal a = account.getCurrentBalance().add(amount);
            account.setCurrentBalance(a);
            accountService.updateAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body("Success: amount debited succesfully" + accountNumber);

        }
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account Number " + accountNumber + " not found.");

}

    public TransactionDto mapToTransaction(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionType(transaction.getTransactionType());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setAccountNumber(transaction.getAccountNumber());
        transactionDto.setTrxDate(transaction.getTrxDate());

        return transactionDto;
    }
}
