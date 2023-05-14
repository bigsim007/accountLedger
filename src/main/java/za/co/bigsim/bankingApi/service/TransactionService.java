package za.co.bigsim.bankingApi.service;

import org.springframework.http.ResponseEntity;
import za.co.bigsim.bankingApi.entity.Transaction;
import za.co.bigsim.bankingApi.entity.dto.TransactionDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransactionService {

    public List<TransactionDto> findTransactionsByAccountNumber(String accountNumber);

    public List<TransactionDto> findTransactionsByAccountNumberAndAccountType(String accountNumber, String accountType);

    public Object creditAccount(BigDecimal amount, String accountNumber);

    public Object debitAccount(BigDecimal amount, String accountNumber);

}
