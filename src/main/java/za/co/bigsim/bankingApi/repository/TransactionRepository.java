package za.co.bigsim.bankingApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.bigsim.bankingApi.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    public Optional<List<Transaction>> findByAccountNumber(String accountNumber);


}
